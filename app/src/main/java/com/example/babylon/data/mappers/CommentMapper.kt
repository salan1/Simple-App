package com.example.babylon.data.mappers

import com.example.babylon.data.entities.CommentDto
import com.example.babylon.data.mappers.base.BaseSourceMapper
import com.example.babylon.domain.models.CommentModel

object CommentMapper : BaseSourceMapper<CommentDto, CommentModel> {

    override fun transformEntity(entity: CommentDto): CommentModel =
            CommentModel(
                    entity.postId,
                    entity.id,
                    entity.name,
                    entity.email,
                    entity.body
            )

    override fun transformDto(dto: CommentModel): CommentDto =
            CommentDto(
                    dto.postId,
                    dto.id,
                    dto.name,
                    dto.email,
                    dto.body
            )
}