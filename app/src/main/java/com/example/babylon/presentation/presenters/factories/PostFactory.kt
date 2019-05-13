package com.example.babylon.presentation.presenters.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babylon.domain.interactors.GetCommentsUseCase
import com.example.babylon.presentation.presenters.impl.PostViewModel
import javax.inject.Inject

class PostFactory @Inject constructor(private val getCommentsUseCase: GetCommentsUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(getCommentsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}