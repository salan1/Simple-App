package com.example.babylon.data.mappers

import com.example.babylon.data.entities.CompanyDto
import com.example.babylon.data.mappers.base.BaseSourceMapper
import com.example.babylon.domain.models.CompanyModel

object CompanyMapper : BaseSourceMapper<CompanyDto, CompanyModel> {

    override fun transformEntity(entity: CompanyDto): CompanyModel =
        CompanyModel(
            entity.name,
            entity.catchPhrase,
            entity.bs
        )

    override fun transformDto(dto: CompanyModel): CompanyDto =
        CompanyDto(
            dto.name,
            dto.catchPhrase,
            dto.bs
        )
}