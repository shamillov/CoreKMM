package com.shamilov.core.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Int,
    val rating: Int?
)