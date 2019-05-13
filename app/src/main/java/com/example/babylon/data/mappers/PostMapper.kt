package com.example.babylon.data.mappers

import com.example.babylon.data.entities.PostDto
import com.example.babylon.data.mappers.base.BaseSourceMapper
import com.example.babylon.domain.models.PostModel

object PostMapper : BaseSourceMapper<PostDto, PostModel> {

    override fun transformEntity(entity: PostDto): PostModel =
        PostModel(
            entity.userId,
            entity.id,
            entity.title,
            entity.body
        )

    override fun transformDto(dto: PostModel): PostDto =
        PostDto(
            dto.userId,
            dto.id,
            dto.title,
            dto.body
        )

}