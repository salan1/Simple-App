package com.example.babylon.data.mappers

import com.example.babylon.data.entities.AddressDto
import com.example.babylon.data.mappers.base.BaseSourceMapper
import com.example.babylon.domain.models.AddressModel

object AddressMapper : BaseSourceMapper<AddressDto, AddressModel> {

    override fun transformEntity(entity: AddressDto): AddressModel =
        AddressModel(
            entity.street,
            entity.suite,
            entity.city,
            entity.zipcode,
            GeoMapper.transformEntity(entity.geo)
        )

    override fun transformDto(dto: AddressModel): AddressDto =
        AddressDto(
            dto.street,
            dto.suite,
            dto.city,
            dto.zipcode,
            GeoMapper.transformDto(dto.geo)
        )
}