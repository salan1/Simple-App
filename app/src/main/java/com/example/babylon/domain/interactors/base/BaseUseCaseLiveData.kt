package com.example.babylon.domain.interactors.base

import androidx.lifecycle.LiveData

interface BaseUseCaseLiveData<T> {
    fun getLiveData(params: Params): LiveData<T>
}