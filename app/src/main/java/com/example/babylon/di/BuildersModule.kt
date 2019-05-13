package com.example.babylon.di

import com.example.babylon.di.scope.ActivityScope
import com.example.babylon.presentation.ui.activities.MainActivity
import com.example.babylon.presentation.ui.activities.PostActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Binds all sub-components within the app.
 */
@Module
internal abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributePostActivity(): PostActivity

}