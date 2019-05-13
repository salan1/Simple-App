package com.example.babylon.presentation.presenters.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babylon.domain.interactors.GetRemoteDataUseCase
import com.example.babylon.domain.interactors.PagePostsUseCase
import com.example.babylon.domain.interactors.RefreshDataUseCase
import com.example.babylon.presentation.presenters.impl.MainViewModel

class MainFactory constructor(
    private val getRemoteDataUseCase: GetRemoteDataUseCase,
    private val pagePostsUseCase: PagePostsUseCase,
    private val refreshDataUseCase: RefreshDataUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getRemoteDataUseCase, pagePostsUseCase, refreshDataUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}