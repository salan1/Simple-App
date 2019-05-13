package com.example.babylon.di

import com.example.babylon.data.repositories.BabylonRepository
import com.example.babylon.di.scope.ActivityScope
import com.example.babylon.domain.interactors.GetCommentsUseCase
import com.example.babylon.domain.interactors.GetRemoteDataUseCase
import com.example.babylon.domain.interactors.PagePostsUseCase
import com.example.babylon.domain.interactors.RefreshDataUseCase
import com.example.babylon.presentation.presenters.factories.MainFactory
import com.example.babylon.presentation.presenters.factories.PostFactory
import dagger.Module
import dagger.Provides


@Module
class MainModule {

    // ViewModel Factories
    @ActivityScope
    @Provides
    internal fun provideMainFactory(
        getRemoteDataUseCase: GetRemoteDataUseCase,
        pagePostsUseCase: PagePostsUseCase,
        refreshDataUseCase: RefreshDataUseCase
    ): MainFactory = MainFactory(getRemoteDataUseCase, pagePostsUseCase, refreshDataUseCase)

    @ActivityScope
    @Provides
    internal fun providePostFactory(
        getCommentsUseCase: GetCommentsUseCase
    ): PostFactory = PostFactory(getCommentsUseCase)

    // Use cases
    @ActivityScope
    @Provides
    internal fun provideGetRemoteDataUseCase(
        babylonRepository: BabylonRepository
    ): GetRemoteDataUseCase =
        GetRemoteDataUseCase(babylonRepository)

    @ActivityScope
    @Provides
    internal fun providePagePostsUseCase(
        babylonRepository: BabylonRepository
    ): PagePostsUseCase =
        PagePostsUseCase(babylonRepository)

    @ActivityScope
    @Provides
    internal fun provideRefreshDataUseCase(
        babylonRepository: BabylonRepository
    ): RefreshDataUseCase =
        RefreshDataUseCase(babylonRepository)

    @ActivityScope
    @Provides
    internal fun provideGetCommentsUseCase(
        babylonRepository: BabylonRepository
    ): GetCommentsUseCase =
        GetCommentsUseCase(babylonRepository)


}