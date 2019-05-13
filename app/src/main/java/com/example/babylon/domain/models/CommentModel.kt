package com.example.babylon.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentModel(
    val postId: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)