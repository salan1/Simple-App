package com.example.babylon.data.repositories.datasources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.babylon.domain.models.UserModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserModel>): Completable

    @Query("SELECT count(*) FROM usermodel")
    fun userCount(): Single<Int>

    @Query("DELETE FROM usermodel")
    fun wipe(): Single<Unit>

}