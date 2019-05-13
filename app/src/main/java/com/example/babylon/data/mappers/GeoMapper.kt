package com.example.babylon.data.mappers

import com.example.babylon.data.entities.GeoDto
import com.example.babylon.data.mappers.base.BaseSourceMapper
import com.example.babylon.domain.models.GeoModel

object GeoMapper : BaseSourceMapper<GeoDto, GeoModel> {

    override fun transformEntity(entity: GeoDto): GeoModel =
        GeoModel(
            entity.lat,
            entity.lng
        )

    override fun transformDto(dto: GeoModel): GeoDto =
        GeoDto(
            dto.lat,
            dto.lng
        )

}