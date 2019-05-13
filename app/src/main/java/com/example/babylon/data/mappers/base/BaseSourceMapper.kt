package com.example.babylon.data.mappers.base

interface BaseSourceMapper<T, F> {
    fun transformEntity(entity: T): F
    fun transformDto(dto: F): T
}