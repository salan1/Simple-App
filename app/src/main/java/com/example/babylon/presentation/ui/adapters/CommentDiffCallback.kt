package com.example.babylon.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.babylon.domain.models.CommentModel

class CommentDiffCallback : DiffUtil.ItemCallback<CommentModel>() {
    override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean =
        oldItem == newItem
}