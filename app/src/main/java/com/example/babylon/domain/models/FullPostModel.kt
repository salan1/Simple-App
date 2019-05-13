package com.example.babylon.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class FullPostModel(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val name: String,
    val username: String,
    val email: String
) : Parcelable