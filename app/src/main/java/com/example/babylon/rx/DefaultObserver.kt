package com.example.babylon.rx

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {
        // Intentionally empty.
    }

    override fun onError(e: Throwable) {
        // Intentionally empty.
    }

    override fun onComplete() {
        // Intentionally empty.
    }

}