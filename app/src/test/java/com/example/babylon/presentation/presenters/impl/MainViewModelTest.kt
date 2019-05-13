package com.example.babylon.presentation.presenters.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.babylon.MockUtils.mockPagedList
import com.example.babylon.domain.interactors.GetRemoteDataUseCase
import com.example.babylon.domain.interactors.PagePostsUseCase
import com.example.babylon.domain.interactors.RefreshDataUseCase
import com.example.babylon.domain.models.FullPostModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getRemoteDataUseCase: GetRemoteDataUseCase

    @Mock
    lateinit var pagePostsUseCase: PagePostsUseCase

    @Mock
    lateinit var refreshDataUseCase: RefreshDataUseCase

    lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(getRemoteDataUseCase, pagePostsUseCase, refreshDataUseCase)
    }

    @Test
    fun getPosts() {
        val post = FullPostModel(
            11, 11, "", "", "", "", ""
        )
        val pagedList = mockPagedList(listOf(post))
        val liveData = MutableLiveData<PagedList<FullPostModel>>().also {
            it.postValue(pagedList)
        }
        whenever(pagePostsUseCase.getLiveData(any())).thenReturn(liveData)
        assert(viewModel.posts.value == pagedList)
    }

    @Test
    fun loadData_PositiveResponse() {
        whenever(getRemoteDataUseCase.execute(any())).thenReturn(Single.just(true))
        viewModel.loadData()
        assert(viewModel.loading.value == false)
    }

    @Test
    fun loadData_ErrorResponse() {
        whenever(getRemoteDataUseCase.execute(any())).thenReturn(Single.error(Error()))
        viewModel.loadData()
        assert(viewModel.loading.value == false)
        assert(viewModel.error.value == "Could not get data")
    }

    @Test
    fun refreshData_PositiveResponse() {
        viewModel.loading.postValue(true)
        whenever(refreshDataUseCase.execute(any())).thenReturn(Single.just(true))
        viewModel.refreshData()
        assert(viewModel.loading.value == false)
    }

    @Test
    fun refreshData_ErrorResponse() {
        viewModel.loading.postValue(true)
        whenever(refreshDataUseCase.execute(any())).thenReturn(Single.error(Error()))
        viewModel.refreshData()
        assert(viewModel.loading.value == false)
    }

    @Test
    fun onCleared() {
    }
}