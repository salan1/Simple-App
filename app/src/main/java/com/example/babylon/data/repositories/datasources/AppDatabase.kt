package com.example.babylon.data.repositories.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.babylon.data.repositories.datasources.room.CommentDao
import com.example.babylon.data.repositories.datasources.room.PostDao
import com.example.babylon.data.repositories.datasources.room.UserDao
import com.example.babylon.domain.models.CommentModel
import com.example.babylon.domain.models.PostModel
import com.example.babylon.domain.models.UserModel
import javax.inject.Singleton

@Singleton
@Database(
    entities = [PostModel::class, UserModel::class, CommentModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
    abstract fun userDao(): UserDao
}