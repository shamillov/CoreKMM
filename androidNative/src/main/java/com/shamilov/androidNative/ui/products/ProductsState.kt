package com.shamilov.androidNative.ui.products

import com.shamilov.androidNative.ui.products.model.ProductData

/**
 * Created by Shamilov on 14.06.2021
 */
sealed class ProductsState {
    class Success(val data: List<ProductData>) : ProductsState()
    class Error(val exception: Throwable) : ProductsState()
    object Loading : ProductsState()
    object Empty : ProductsState()
}

sealed class ProductsAction {
    class OpenProduct(id: Int) : ProductsAction()
    class ShowMessage(message: String) : ProductsAction()
}