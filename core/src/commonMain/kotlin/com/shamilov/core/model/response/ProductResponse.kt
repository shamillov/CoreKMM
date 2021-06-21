package com.shamilov.core.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Int,
    val rating: Int?,
    @SerialName("media")
    val image: List<MediaResponse>?
)