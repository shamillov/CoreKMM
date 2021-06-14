package com.shamilov.androidNative.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductsViewModel(
    private val id: Int,
    private val repository: RemoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProductsState>(ProductsState.Loading)
    val state: StateFlow<ProductsState> = _state

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.value = repository.loadProducts(id)
                .fold(
                    { products ->
                        if (products.isNullOrEmpty()) {
                            ProductsState.Empty
                        } else {
                            ProductsState.Success(products)
                        }
                    },
                    { exception ->
                        ProductsState.Error(exception)
                    }
                )
        }
    }
}