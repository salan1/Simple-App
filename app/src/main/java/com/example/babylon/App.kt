package com.example.babylon

import android.os.Looper
import com.example.babylon.di.app.DaggerAppComponent
import com.example.babylon.utils.logging.NotLoggingTree
import dagger.android.DaggerApplication
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App : DaggerApplication() {

    private val appComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector() = appComponent

    override fun onCreate() {
        super.onCreate()

        //Init RxJava async lib
        val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { asyncMainThreadScheduler }

        //Display logs only during debugging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(NotLoggingTree())
        }
    }

    companion object {
        val executor: ExecutorService = Executors.newFixedThreadPool(6)
    }
}