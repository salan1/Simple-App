package com.example.babylon.utils.logging

object LogUtil {

    fun getLogTag(objectInstance: Any): String {
        val clazz = objectInstance::class
        return String.format("%s{%s}", clazz::class.simpleName, Integer.toHexString(clazz.hashCode()))
    }
}