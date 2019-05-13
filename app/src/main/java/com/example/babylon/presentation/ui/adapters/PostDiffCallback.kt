package com.example.babylon.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.babylon.domain.models.FullPostModel

class PostDiffCallback : DiffUtil.ItemCallback<FullPostModel>() {
    override fun areItemsTheSame(oldItem: FullPostModel, newItem: FullPostModel): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: FullPostModel, newItem: FullPostModel): Boolean =
        oldItem == newItem
}