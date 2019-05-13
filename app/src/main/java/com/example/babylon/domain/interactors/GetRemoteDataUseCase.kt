package com.example.babylon.domain.interactors

import com.example.babylon.data.repositories.BabylonRepository
import com.example.babylon.domain.interactors.base.BaseUseCaseSingle
import com.example.babylon.domain.interactors.base.Params
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetRemoteDataUseCase @Inject constructor(private val babylonRepository: BabylonRepository) :
    BaseUseCaseSingle<Boolean>() {

    override fun getSingle(params: Params): Single<Boolean> =
        Single.zip(
            babylonRepository.getRemoteComments(),
            babylonRepository.getRemotePosts(),
            babylonRepository.getRemoteUsers(),
            Function3 { _: Boolean, _: Boolean, _: Boolean ->
                true
            }
        )

}