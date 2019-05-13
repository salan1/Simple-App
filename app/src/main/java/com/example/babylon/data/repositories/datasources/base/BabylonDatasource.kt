package com.example.babylon.data.repositories.datasources.base

import com.example.babylon.BuildConfig

object BabylonDatasource {

    val HOST: String =
            if (BuildConfig.DEBUG) "http://jsonplaceholder.typicode.com" else "http://jsonplaceholder.typicode.com"

}