package com.shamilov.androidNative.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Shamilov on 24.05.2021
 */
interface CategoryViewModel {
    val state: StateFlow<CategoryState>

    fun openProducts(id: Int)
}

class CategoryViewModelImpl(
    private val repository: RemoteRepository
) : ViewModel(), CategoryViewModel {

    override val state = MutableStateFlow<CategoryState>(CategoryState.Loading)

    override fun openProducts(id: Int) {
        TODO("Not yet implemented")
    }

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            state.value = repository.loadCategories()
                .fold(
                    onSuccess = { categories ->
                        if (categories.isNullOrEmpty()) {
                            CategoryState.IsEmpty
                        } else {
                            CategoryState.Success(categories)
                        }
                    },
                    onFailure = { throwable ->
                        CategoryState.Error(throwable)
                    }
                )
        }
    }
}