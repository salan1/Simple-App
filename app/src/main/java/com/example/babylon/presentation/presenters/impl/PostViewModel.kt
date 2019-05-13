package com.example.babylon.presentation.presenters.impl

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.babylon.domain.interactors.GetCommentsUseCase
import com.example.babylon.domain.interactors.base.Params
import com.example.babylon.domain.models.CommentModel
import com.example.babylon.domain.models.FullPostModel
import com.example.babylon.presentation.presenters.base.BaseViewModel
import com.example.babylon.presentation.ui.activities.PostActivity

class PostViewModel constructor(
    private val getCommentsUseCase: GetCommentsUseCase
) : BaseViewModel<PostActivity>() {

    lateinit var post: FullPostModel

    fun getComments(postId: Int): LiveData<PagedList<CommentModel>> {
        val params = Params.create()
        params.putInt(GetCommentsUseCase.PARAM_KEY_POST_ID, postId)
        return getCommentsUseCase.getLiveData(params)
    }

}