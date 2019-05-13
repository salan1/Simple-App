package com.example.babylon.rx

import androidx.annotation.NonNull
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

@Suppress("UNCHECKED_CAST")
object RxBus {

    private val sSubjectMap = HashMap<String, PublishSubject<*>>()
    private val sBehaviorMap = HashMap<String, BehaviorSubject<*>>()
    private val sSubscriptionsMap = HashMap<Any, CompositeDisposable>()

    /**
     * Get the subject or create it if it's not already in memory.
     */
    @NonNull
    private fun <T> getSubject(id: String): PublishSubject<T> {
        return if (sSubjectMap.containsKey(id)) {
            sSubjectMap[id] as PublishSubject<T>
        } else {
            val subject: PublishSubject<T> = PublishSubject.create()
            subject.subscribeOn(AndroidSchedulers.mainThread())
            sSubjectMap[id] = subject
            subject
        }
    }

    /**
     * Get the subject or create it if it's not already in memory.
     */
    @NonNull
    private fun <T> getBehavior(id: String): BehaviorSubject<T> {
        return if (sBehaviorMap.containsKey(id)) {
            sBehaviorMap[id] as BehaviorSubject<T>
        } else {
            val subject: BehaviorSubject<T> = BehaviorSubject.create()
            subject.subscribeOn(AndroidSchedulers.mainThread())
            sBehaviorMap[id] = subject
            subject
        }
    }

    fun <T> getBehaviorValue(id: String): T? {
        return sBehaviorMap[id]?.value as T
    }

    private fun getCompositeDisposable(`object`: Any): CompositeDisposable {
        var compositeDisposable: CompositeDisposable? = sSubscriptionsMap[`object`]
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            sSubscriptionsMap[`object`] = compositeDisposable
        }
        return compositeDisposable
    }

    fun <T> subscribe(id: String, lifecycle: Any, action: Consumer<T>) {
        val disposable = getSubject<T>(id).subscribe(action)
        getCompositeDisposable(lifecycle).add(disposable)
    }

    fun <T> subscribeBehavior(id: String, lifecycle: Any, action: Consumer<T>) {
        val behavior: BehaviorSubject<T> = getBehavior(id)
        val disposable = behavior.subscribe(action)
        getCompositeDisposable(lifecycle).add(disposable)
    }

    fun unregister(lifecycle: Any) {
        //We have to remove the composition from the map, because once you dispose it can't be used anymore
        val compositeDisposable = sSubscriptionsMap.remove(lifecycle)
        compositeDisposable?.dispose()
    }

    fun <T> publish(id: String, message: T) {
        getSubject<T>(id).onNext(message)
    }

    fun <T> publishBehavior(id: String, message: T) {
        getBehavior<T>(id).onNext(message)
    }

    fun checkSubscription(id: String): Boolean {
        return sSubjectMap.containsKey(id) || sBehaviorMap.containsKey(id)
    }

}