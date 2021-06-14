package com.shamilov.androidNative.ui.products

import com.shamilov.core.model.response.ProductResponse

/**
 * Created by Shamilov on 14.06.2021
 */
sealed class ProductsState {
    class Success(val data: List<ProductResponse>) : ProductsState()
    class Error(val exception: Throwable) : ProductsState()
    object Loading : ProductsState()
    object Empty : ProductsState()
}

sealed class ProductsAction {
    class OpenProduct(id: Int) : ProductsAction()
}