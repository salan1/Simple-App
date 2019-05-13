package com.example.babylon.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babylon.R
import com.example.babylon.presentation.presenters.factories.MainFactory
import com.example.babylon.presentation.presenters.impl.MainViewModel
import com.example.babylon.presentation.ui.adapters.PostAdapter
import com.example.babylon.presentation.ui.base.BaseActivity
import com.example.babylon.utils.ui.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: MainFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.loadData()

        // Setup progressBar
        viewModel.loading.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })

        swipeRefresh.setOnRefreshListener {
            if (!isNetworkConnected()) {
                showNoNetwork()
                swipeRefresh.isRefreshing = false
            }
            viewModel.refreshData()
        }

        // Setup recyclerView
        val adapter = PostAdapter(this) { post, title ->
            Timber.i("Post clicked ${post.id} ${post.username}")
            PostActivity.startActivity(this, post, this, title)
        }

        recyclerView_posts.layoutManager = LinearLayoutManager(context)
        recyclerView_posts.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 8))
        recyclerView_posts.adapter = adapter

        viewModel.posts.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun getExtras(_intent: Intent) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

    override fun showNoNetwork() {
        Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG).show()
    }

}
