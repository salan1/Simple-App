package com.example.babylon.domain.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Embedded
    val address: AddressModel,
    val phone: String,
    val website: String,
    @Embedded
    val company: CompanyModel
)