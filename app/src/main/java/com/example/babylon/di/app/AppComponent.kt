package com.example.babylon.di.app

import android.app.Application
import com.example.babylon.App
import com.example.babylon.di.BuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: App)
    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
