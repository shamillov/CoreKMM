package com.shamilov.androidNative.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.androidNative.ui.products.mapper.ProductItemMapper
import com.shamilov.core.model.response.ProductResponse
import com.shamilov.core.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Shamilov on 14.06.2021
 */
interface ProductsViewModel {
    val state: StateFlow<ProductsState>

    fun openProduct(id: Int)
}

class ProductsViewModelImpl(
    private val id: Int,
    private val repository: RemoteRepository,
    private val mapper: ProductItemMapper
) : ViewModel(), ProductsViewModel {

    override val state = MutableStateFlow<ProductsState>(ProductsState.Loading)

    override fun openProduct(id: Int) {
        TODO("Not yet implemented")
    }

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            state.value = repository.loadProducts(id)
                .fold(
                    onSuccess = { products ->
                        handleSuccessResult(products)
                    },
                    onFailure = { exception ->
                        ProductsState.Error(exception)
                    }
                )
        }
    }

    private fun handleSuccessResult(list: List<ProductResponse>): ProductsState {
        return if (list.isNullOrEmpty()) {
            ProductsState.Empty
        } else {
            ProductsState.Success(mapper.mapProductsList(list))
        }
    }
}