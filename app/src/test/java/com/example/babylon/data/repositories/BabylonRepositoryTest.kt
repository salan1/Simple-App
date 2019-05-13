package com.example.babylon.data.repositories

import com.example.babylon.data.entities.*
import com.example.babylon.data.mappers.UserMapper
import com.example.babylon.data.repositories.datasources.AppDatabase
import com.example.babylon.data.repositories.datasources.BabylonDatasource
import com.example.babylon.data.repositories.datasources.room.CommentDao
import com.example.babylon.data.repositories.datasources.room.PostDao
import com.example.babylon.data.repositories.datasources.room.UserDao
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class BabylonRepositoryTest {

    @Mock
    lateinit var babylonDatasource: BabylonDatasource

    @Mock
    lateinit var database: AppDatabase

    @Mock
    lateinit var userDao: UserDao

    @Mock
    lateinit var postDao: PostDao

    @Mock
    lateinit var commentDao: CommentDao

    @Mock
    lateinit var userMapper: UserMapper

    private lateinit var babylonRepository: BabylonRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        babylonRepository = BabylonRepository(babylonDatasource, database)
    }

    @Test
    fun wipeLocalData_PositiveResponse() {
        whenever(database.userDao()).thenReturn(userDao)
        whenever(database.postDao()).thenReturn(postDao)
        whenever(database.commentDao()).thenReturn(commentDao)

        whenever(database.userDao().wipe()).thenReturn(Single.just(Unit))
        whenever(database.postDao().wipe()).thenReturn(Single.just(Unit))
        whenever(database.commentDao().wipe()).thenReturn(Single.just(Unit))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.wipeLocalData().subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool ->
            bool
        }
    }

    @Test
    fun givenNonEmptyDb_getRemotePosts_PositiveResponse() {
        whenever(database.postDao()).thenReturn(postDao)
        whenever(database.postDao().postCount()).thenReturn(Single.just(5))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.getRemotePosts().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool: Boolean -> bool }
    }

    @Test
    fun givenEmptyDb_getRemotePosts_PositiveResponse() {
        val post = PostDto(22, 11, "", "")

        whenever(database.postDao()).thenReturn(postDao)
        whenever(database.postDao().postCount()).thenReturn(Single.just(0))
        whenever(database.postDao().insertPosts(any())).thenReturn(Completable.complete())
        whenever(babylonDatasource.getPosts()).thenReturn(Single.just(listOf<PostDto>(post)))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.getRemotePosts().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool: Boolean -> bool }
    }

    @Test
    fun givenNonEmptyDb_GetRemoteComments_PositiveResponse() {
        whenever(database.commentDao()).thenReturn(commentDao)
        whenever(database.commentDao().commentCount()).thenReturn(Single.just(5))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.getRemoteComments().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool: Boolean -> bool }
    }

    @Test
    fun givenEmptyDb_GetRemoteComments_PositiveResponse() {
        val comment = CommentDto(22, 11, "", "", "")

        whenever(database.commentDao()).thenReturn(commentDao)
        whenever(database.commentDao().commentCount()).thenReturn(Single.just(0))
        whenever(database.commentDao().insertComments(any())).thenReturn(Completable.complete())
        whenever(babylonDatasource.getComments()).thenReturn(Single.just(listOf<CommentDto>(comment)))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.getRemoteComments().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool: Boolean -> bool }
    }

    @Test
    fun givenNonEmptyDb_GetRemoteUsers_PositiveResponse() {
        whenever(database.userDao()).thenReturn(userDao)
        whenever(database.userDao().userCount()).thenReturn(Single.just(5))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.getRemoteUsers().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool: Boolean -> bool }
    }

    @Test
    fun givenEmptyDb_GetRemoteUsers_PositiveResponse() {
        val user = UserDto(
            22, "", "", "",
            AddressDto(
                "", "", "", "",
                GeoDto(1.0, 1.0)
            ), "", "", CompanyDto("", "", "")
        )

        whenever(database.userDao()).thenReturn(userDao)
        whenever(database.userDao().insertUsers(any())).thenReturn(Completable.complete())
        whenever(database.userDao().userCount()).thenReturn(Single.just(0))
        whenever(babylonDatasource.getUsers()).thenReturn(Single.just(listOf<UserDto>(user)))

        val testObserver = TestObserver<Boolean>()
        babylonRepository.getRemoteUsers().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue { bool: Boolean -> bool }
    }
}