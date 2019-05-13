package com.example.babylon.domain.interactors

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.babylon.data.repositories.BabylonRepository
import com.example.babylon.domain.interactors.base.BaseUseCaseLiveData
import com.example.babylon.domain.interactors.base.Params
import com.example.babylon.domain.models.FullPostModel
import javax.inject.Inject

class PagePostsUseCase @Inject constructor(
    private val babylonRepository: BabylonRepository
) : BaseUseCaseLiveData<PagedList<FullPostModel>> {

    override fun getLiveData(params: Params): LiveData<PagedList<FullPostModel>> =
            babylonRepository.pagePosts()
}