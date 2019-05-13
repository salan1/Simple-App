package com.example.babylon.presentation.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.babylon.utils.network.NetworkUtils
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber


abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("onCreate")
        super.onCreate(savedInstanceState)
    }

    abstract fun getExtras(_intent: Intent)

    protected val context: Context
        get() = this

    protected fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    abstract fun showLoading()
    abstract fun hideLoading()

    abstract fun showMessage(message: String)
    abstract fun showNoNetwork()

    protected fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            if (imm != null) imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // Detach view from presenter on activity destroy
    override fun onDestroy() {
        Timber.v("onDestroy activity")
        super.onDestroy()
    }
}