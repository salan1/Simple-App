package com.example.babylon.data.repositories.datasources

import com.example.babylon.api.BabylonApi
import com.example.babylon.data.entities.CommentDto
import com.example.babylon.data.entities.PostDto
import com.example.babylon.data.entities.UserDto
import com.example.babylon.data.repositories.datasources.clients.BabylonClient
import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class BabylonDatasourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var datasource: BabylonDatasource

    private val FILE_MOCK_POSTS_SUCCESS = "ResponsePostsSuccess.json"
    private val FILE_MOCK_COMMENTS_SUCCESS = "ResponseCommentsSuccess.json"
    private val FILE_MOCK_USERS_SUCCESS = "ResponseUsersSuccess.json"


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()


        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val client = retrofit.create(BabylonApi::class.java)
        datasource = BabylonDatasource(client)
    }

    @After
    @Throws
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetPostsReturnCorrectResult() {
        val testObserver = TestObserver<List<PostDto>>()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    getJson(FILE_MOCK_POSTS_SUCCESS)
                )
        )
        datasource.getPosts().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValueAt(0) {
            it.size == 6
        }
    }

    @Test
    fun testGetUsersReturnICorrectResult() {
        val testObserver = TestObserver<List<UserDto>>()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    getJson(FILE_MOCK_USERS_SUCCESS)
                )
        )
        datasource.getUsers().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValueAt(0) {
            it.size == 10
        }
    }

    @Test
    fun testGetCommentsReturnICorrectResult() {
        val testObserver = TestObserver<List<CommentDto>>()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    getJson(FILE_MOCK_COMMENTS_SUCCESS)
                )
        )
        datasource.getComments().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValueAt(0) {
            it.size == 9
        }
    }

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

}