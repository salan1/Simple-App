package com.example.babylon.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.babylon.R
import com.example.babylon.domain.models.CommentModel
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(private val context: Context) :
    PagedListAdapter<CommentModel, CommentAdapter.CommentViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        getItem(position).also { comment ->
            comment?.let { holder.bindTo(it) }
        }
    }

    inner class CommentViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bindTo(comment: CommentModel) {
            with(item) {
                textView_message.text = comment.body
                textView_email.text = comment.email
            }
        }
    }
}