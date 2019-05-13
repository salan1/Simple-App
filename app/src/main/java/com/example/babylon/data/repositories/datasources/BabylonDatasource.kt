package com.example.babylon.data.repositories.datasources

import com.example.babylon.api.BabylonApi
import com.example.babylon.data.entities.CommentDto
import com.example.babylon.data.entities.PostDto
import com.example.babylon.data.entities.UserDto
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BabylonDatasource @Inject constructor(
    private val babylonClient: BabylonApi
) {

    fun getPosts(): Single<List<PostDto>> =
        babylonClient.getPosts()

    fun getUsers(): Single<List<UserDto>> =
        babylonClient.getUsers()

    fun getComments(): Single<List<CommentDto>> =
        babylonClient.getComments()

}