package com.shamilov.androidNative.ui.products.model

import java.io.Serializable

/**
 * Created by Shamilov on 17.06.2021
 */
data class ProductData(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Int,
    val rating: Int?,
    val image: String
) : Serializable