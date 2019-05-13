package com.example.babylon.domain.models

import androidx.room.Embedded

class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Embedded
    val geo: GeoModel
)