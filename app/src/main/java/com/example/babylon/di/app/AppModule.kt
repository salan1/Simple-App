package com.example.babylon.di.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.babylon.api.BabylonApi
import com.example.babylon.data.repositories.BabylonRepository
import com.example.babylon.data.repositories.datasources.AppDatabase
import com.example.babylon.data.repositories.datasources.BabylonDatasource
import com.example.babylon.data.repositories.datasources.clients.BabylonClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.baseContext

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "offlineDB")
            .fallbackToDestructiveMigration()
            .build()

    // Clients
    @Provides
    @Singleton
    internal fun provideBabylonClient(): BabylonApi =
        BabylonClient().babylonApi


    // Datasources
    @Provides
    @Singleton
    internal fun provideBabylonDatasource(
        client: BabylonApi
    ): BabylonDatasource = BabylonDatasource(client)


    // Repos
    @Provides
    @Singleton
    internal fun provideBabylonRepository(
        babylonDatasource: BabylonDatasource,
        appDatabase: AppDatabase
    ) = BabylonRepository(
        babylonDatasource,
        appDatabase
    )


}
