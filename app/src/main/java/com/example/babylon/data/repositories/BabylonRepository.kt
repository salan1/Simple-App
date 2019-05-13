package com.example.babylon.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.babylon.data.mappers.CommentMapper
import com.example.babylon.data.mappers.PostMapper
import com.example.babylon.data.mappers.UserMapper
import com.example.babylon.data.repositories.datasources.AppDatabase
import com.example.babylon.data.repositories.datasources.BabylonDatasource
import com.example.babylon.domain.models.CommentModel
import com.example.babylon.domain.models.FullPostModel
import io.reactivex.Single
import io.reactivex.functions.Function3
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BabylonRepository @Inject constructor(
    private val source: BabylonDatasource,
    private val database: AppDatabase
) {

    fun wipeLocalData(): Single<Boolean> {
        return Single.zip(
            database.userDao().wipe(),
            database.commentDao().wipe(),
            database.postDao().wipe(),
            Function3 { _: Unit, _: Unit, _: Unit ->
                true
            })
    }

    fun getComments(postID: Int): LiveData<PagedList<CommentModel>> =
        LivePagedListBuilder(database.commentDao().getComments(postID), 10).build()

    fun getRemotePosts(): Single<Boolean> =
        database.postDao().postCount().flatMap {
            if (it == 0) {
                // Get posts from remote
                source.getPosts().flatMap { result ->
                    result.map(PostMapper::transformEntity).let { posts ->
                        database.postDao().insertPosts(posts).toSingle {
                            Timber.i("Cached posts")
                            true
                        }
                    }
                }
            } else {
                Single.just(true)
            }
        }

    /**
     * Page through posts from local db
     * @return [LiveData] paging with PostModel [PagedList]
     */
    fun pagePosts(): LiveData<PagedList<FullPostModel>> =
        LivePagedListBuilder(database.postDao().pageFullPosts(), 10).build()

    fun getRemoteComments(): Single<Boolean> =
        database.commentDao().commentCount().flatMap {
            if (it == 0) {
                // Get comments from remote
                source.getComments().flatMap { result ->
                    result.map(CommentMapper::transformEntity).let { comments ->
                        database.commentDao().insertComments(comments).toSingle {
                            Timber.i("Cached comments")
                            true
                        }
                    }
                }
            } else {
                Single.just(true)
            }
        }

    fun getRemoteUsers(): Single<Boolean> =
        database.userDao().userCount().flatMap {
            if (it == 0) {
                // Get comments from remote
                source.getUsers().flatMap { result ->
                    result.map(UserMapper::transformEntity).let { users ->
                        database.userDao().insertUsers(users).toSingle {
                            Timber.i("Cached users")
                            true
                        }
                    }
                }
            } else {
                Single.just(true)
            }
        }

}