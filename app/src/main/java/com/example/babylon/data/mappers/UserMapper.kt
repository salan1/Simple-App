package com.example.babylon.data.mappers

import com.example.babylon.data.entities.UserDto
import com.example.babylon.data.mappers.base.BaseSourceMapper
import com.example.babylon.domain.models.UserModel

object UserMapper : BaseSourceMapper<UserDto, UserModel> {

    override fun transformEntity(entity: UserDto): UserModel =
        UserModel(
            entity.id,
            entity.name,
            entity.username,
            entity.email,
            AddressMapper.transformEntity(entity.address),
            entity.phone,
            entity.website,
            CompanyMapper.transformEntity(entity.company)
        )

    override fun transformDto(dto: UserModel): UserDto =
        UserDto(
            dto.id,
            dto.name,
            dto.username,
            dto.email,
            AddressMapper.transformDto(dto.address),
            dto.phone,
            dto.website,
            CompanyMapper.transformDto(dto.company)
        )
}