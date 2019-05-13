package com.example.babylon.data.repositories.datasources.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.babylon.domain.models.FullPostModel
import com.example.babylon.domain.models.PostModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostModel>): Completable

    @Query("SELECT count(*) FROM postmodel")
    fun postCount(): Single<Int>

    @Query("SELECT * FROM postmodel")
    fun pagePosts(): DataSource.Factory<Int, PostModel>

    @Query("SELECT userId, postmodel.id, title, body, name, username, email FROM postmodel INNER JOIN usermodel WHERE userId == usermodel.id ")
    fun pageFullPosts(): DataSource.Factory<Int, FullPostModel>

    @Query("DELETE FROM postmodel")
    fun wipe(): Single<Unit>

}