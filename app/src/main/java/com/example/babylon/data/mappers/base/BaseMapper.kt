package com.example.babylon.data.mappers.base

interface BaseMapper<T, F> {
    fun transformEntity(entity: T): F
    fun transformModel(model: F): T
}