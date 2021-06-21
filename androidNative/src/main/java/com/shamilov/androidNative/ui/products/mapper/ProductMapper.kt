package com.shamilov.androidNative.ui.products.mapper

import com.shamilov.androidNative.ui.products.model.ProductData
import com.shamilov.core.model.response.ProductResponse

/**
 * Created by Shamilov on 17.06.2021
 */
class ProductItemMapper {
    fun mapProductsList(list: List<ProductResponse>): List<ProductData> {
        return list.map {
            ProductData(
                id = it.id,
                name = it.name,
                description = it.description,
                price = it.price,
                rating = it.rating,
                image = it.image?.firstOrNull()?.url ?: ""
            )
        }
    }
}