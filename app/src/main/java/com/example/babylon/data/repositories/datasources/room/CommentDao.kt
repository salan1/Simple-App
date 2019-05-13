package com.example.babylon.data.repositories.datasources.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.babylon.domain.models.CommentModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: CommentModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentModel>): Completable

    @Query("SELECT count(*) FROM commentmodel")
    fun commentCount(): Single<Int>

    @Query("SELECT * FROM commentmodel WHERE  postId =:postId")
    fun getComments(postId: Int): DataSource.Factory<Int, CommentModel>

    @Query("DELETE FROM commentmodel")
    fun wipe(): Single<Unit>

}