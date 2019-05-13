package com.example.babylon.data.entities

data class CommentDto(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
)