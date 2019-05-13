package com.example.babylon.rx

import io.reactivex.observers.DisposableSingleObserver

open class DefaultSingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
        // Intentionally empty.
    }

    override fun onError(e: Throwable) {
        // Intentionally empty.
    }

}