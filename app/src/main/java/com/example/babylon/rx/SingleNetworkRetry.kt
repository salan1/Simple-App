package com.example.babylon.rx

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import org.reactivestreams.Publisher
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class SingleNetworkRetry constructor(private val maxRetries: Int) : Function<Flowable<out Throwable>, Publisher<*>> {

    @Throws(Exception::class)
    override fun apply(attemps: Flowable<out Throwable>): Publisher<*>? {
        return attemps.zipWith(Flowable.range(1, maxRetries + 1), BiFunction<Throwable, Int, Int> { error, retryCount ->
            if (error is UnknownHostException || retryCount > maxRetries) {
                throw error
            } else {
                retryCount
            }
        }).flatMap { retryCount ->
            Flowable.timer(
                Math.pow(2.toDouble(), retryCount.toDouble()).toLong(),
                TimeUnit.SECONDS
            )
        }
    }
}
