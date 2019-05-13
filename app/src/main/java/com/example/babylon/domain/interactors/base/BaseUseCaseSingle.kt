package com.example.babylon.domain.interactors.base

import com.example.babylon.App
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCaseSingle<T> {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun getSingle(params: Params): Single<T>

    fun execute(params: Params, observer: DisposableSingleObserver<T>): Single<T> {
        return this.getSingle(params)
            .subscribeOn(Schedulers.from(App.executor))
            .observeOn(AndroidSchedulers.mainThread()).also {
                addDisposable(it.subscribeWith(observer))
            }
    }

    fun execute(params: Params): Single<T> {
        return this.getSingle(params)
            .subscribeOn(Schedulers.from(App.executor))
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

}