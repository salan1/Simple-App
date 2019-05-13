package com.example.babylon.api

import com.example.babylon.data.entities.CommentDto
import com.example.babylon.data.entities.PostDto
import com.example.babylon.data.entities.UserDto
import io.reactivex.Single
import retrofit2.http.GET

interface BabylonApi {

    @GET("posts")
    fun getPosts(): Single<List<PostDto>>

    @GET("users")
    fun getUsers(): Single<List<UserDto>>

    @GET("comments")
    fun getComments(): Single<List<CommentDto>>

}