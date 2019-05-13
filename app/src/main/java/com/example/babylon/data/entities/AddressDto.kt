package com.example.babylon.data.entities

data class AddressDto(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: GeoDto
)