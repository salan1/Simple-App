package com.example.babylon.domain.interactors

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.babylon.data.repositories.BabylonRepository
import com.example.babylon.domain.interactors.base.BaseUseCaseLiveData
import com.example.babylon.domain.interactors.base.Params
import com.example.babylon.domain.models.CommentModel
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(private val babylonRepository: BabylonRepository) :
    BaseUseCaseLiveData<PagedList<CommentModel>> {

    override fun getLiveData(params: Params): LiveData<PagedList<CommentModel>> =
        params.getInt(PARAM_KEY_POST_ID, 0).let { postId ->
            babylonRepository.getComments(postId)
        }

    companion object {
        const val PARAM_KEY_POST_ID = "param_post"
    }

}