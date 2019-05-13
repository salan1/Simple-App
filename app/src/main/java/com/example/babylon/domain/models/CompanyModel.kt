package com.example.babylon.domain.models

import androidx.room.ColumnInfo

class CompanyModel(
    @ColumnInfo(name = "companyName")
    val name: String,
    val catchPhrase: String,
    val bs: String
)