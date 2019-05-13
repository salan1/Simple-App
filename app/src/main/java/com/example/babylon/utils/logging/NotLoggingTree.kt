package com.example.babylon.utils.logging

import timber.log.Timber

class NotLoggingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {}
}