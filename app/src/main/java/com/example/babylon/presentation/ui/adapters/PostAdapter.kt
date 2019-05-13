package com.example.babylon.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.babylon.R
import com.example.babylon.domain.models.FullPostModel
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(private val context: Context, private val callback: (post: FullPostModel, title: TextView) -> Unit) :
    PagedListAdapter<FullPostModel, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position).also { post ->
            post?.let { holder.bindTo(it) }
        }
    }

    inner class PostViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        @SuppressLint("DefaultLocale")
        fun bindTo(post: FullPostModel) {
            with(item) {
                textView_title.text = post.title.capitalize()
                textView_desc.text = post.body.capitalize()
                textView_user.text = context.resources.getString(R.string.user_label, post.username)
                setOnClickListener {
                    callback.invoke(post, textView_title)
                }
            }

        }
    }
}