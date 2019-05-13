package com.example.babylon.presentation.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babylon.R
import com.example.babylon.domain.models.FullPostModel
import com.example.babylon.presentation.presenters.factories.PostFactory
import com.example.babylon.presentation.presenters.impl.PostViewModel
import com.example.babylon.presentation.ui.adapters.CommentAdapter
import com.example.babylon.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.content_post.*
import timber.log.Timber
import javax.inject.Inject

class PostActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: PostFactory
    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java)

        getExtras(intent)
        setupView()
    }

    override fun getExtras(_intent: Intent) {
        viewModel.post = _intent.getParcelableExtra<FullPostModel>(EXTRA_POST)
    }

    private fun setupView() {
        textView_title.text = viewModel.post.title
        textView_desc.text = viewModel.post.body
        textView_user.text = context.resources.getString(R.string.user_label, viewModel.post.username)

        // Setup RecyclerView for comments
        val adapter = CommentAdapter(this)
        recyclerView_comments.layoutManager = LinearLayoutManager(context)
        recyclerView_comments.adapter = adapter

        viewModel.getComments(viewModel.post.id).observe(this, Observer {
            Timber.i("Got list ${it.size}")
            adapter.submitList(it)
        })
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

    override fun showNoNetwork() {
    }

    companion object {
        fun startActivity(context: Context, post: FullPostModel, activity: MainActivity, textView: TextView) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, textView, "postTitle")

            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(EXTRA_POST, post)
            context.startActivity(intent, options.toBundle())
        }

        const val EXTRA_POST = "extra_post"
    }

}
