package com.example.babylon.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val throwable: Throwable) : Result<T>()
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success