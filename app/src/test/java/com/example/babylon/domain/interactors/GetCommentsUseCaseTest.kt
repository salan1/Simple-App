package com.example.babylon.domain.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.babylon.MockUtils
import com.example.babylon.data.repositories.BabylonRepository
import com.example.babylon.domain.interactors.GetCommentsUseCase.Companion.PARAM_KEY_POST_ID
import com.example.babylon.domain.interactors.base.Params
import com.example.babylon.domain.models.CommentModel
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

@RunWith(JUnit4::class)
class GetCommentsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var babylonRepository: BabylonRepository

    lateinit var getCommentsUseCase: GetCommentsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCommentsUseCase = GetCommentsUseCase(babylonRepository)
    }

    @Test
    fun givenValidParam_getLiveData_PositiveResponse() {
        val comment = CommentModel(113, 123, "", "", "")
        val pagedList = MockUtils.mockPagedList(listOf(comment))
        val liveData = MutableLiveData<PagedList<CommentModel>>().also {
            it.postValue(pagedList)
        }
        whenever(babylonRepository.getComments(anyInt())).thenReturn(liveData)
        val params = Params.create()
        params.putInt(PARAM_KEY_POST_ID, 11)
        assert(getCommentsUseCase.getLiveData(params).value?.first() == comment)
    }

    @Test
    fun givenValidParam_getLiveData_EmptyResponse() {
        val pagedList = MockUtils.mockPagedList(listOf<CommentModel>())
        val liveData = MutableLiveData<PagedList<CommentModel>>().also {
            it.postValue(pagedList)
        }
        whenever(babylonRepository.getComments(anyInt())).thenReturn(liveData)
        val params = Params.create()
        params.putInt(PARAM_KEY_POST_ID, 11)
        assert(getCommentsUseCase.getLiveData(params).value?.size == 0)
    }

    @Test
    fun givenInvalidParam_getLiveData() {
        val pagedList = MockUtils.mockPagedList(listOf<CommentModel>())
        val liveData = MutableLiveData<PagedList<CommentModel>>().also {
            it.postValue(pagedList)
        }
        whenever(babylonRepository.getComments(anyInt())).thenReturn(liveData)
        val params = Params.create()
        params.putString(PARAM_KEY_POST_ID, "")
        println(getCommentsUseCase.getLiveData(params))
        assert(getCommentsUseCase.getLiveData(params).value?.size == 0)
    }

}