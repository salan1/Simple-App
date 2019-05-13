package com.example.babylon.presentation.presenters.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.babylon.domain.interactors.GetRemoteDataUseCase
import com.example.babylon.domain.interactors.PagePostsUseCase
import com.example.babylon.domain.interactors.RefreshDataUseCase
import com.example.babylon.domain.interactors.base.Params
import com.example.babylon.domain.models.FullPostModel
import com.example.babylon.presentation.presenters.base.BaseViewModel
import com.example.babylon.presentation.ui.activities.MainActivity
import com.example.babylon.rx.DefaultSingleObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class MainViewModel(
    private val getRemoteDataUseCase: GetRemoteDataUseCase,
    private val pagePostsUseCase: PagePostsUseCase,
    private val refreshDataUseCase: RefreshDataUseCase
) : BaseViewModel<MainActivity>() {

    val posts: LiveData<PagedList<FullPostModel>> by lazy { pagePostsUseCase.getLiveData(Params.EMPTY) }
    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun loadData() {
        loading.postValue(true)
        mCompositeDisposable += getRemoteDataUseCase.execute(Params.EMPTY)
            .subscribeWith(object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    Timber.i("Retrieved data")
                    loading.postValue(false)
                }

                override fun onError(e: Throwable) {
                    Timber.d(e)
                    loading.postValue(false)
                    error.postValue("Could not get data")
                }
            })
    }

    fun refreshData() {
        mCompositeDisposable += refreshDataUseCase.execute(Params.EMPTY)
            .subscribeWith(object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    loading.postValue(false)
                }

                override fun onError(e: Throwable) {
                    Timber.d(e)
                    loading.postValue(false)
                }
            })
    }

}