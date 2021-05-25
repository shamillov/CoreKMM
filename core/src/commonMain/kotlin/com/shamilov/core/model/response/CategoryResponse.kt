package com.shamilov.core.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: Int,
    val name: String,
    @SerialName("media")
    val image: MediaResponse
)