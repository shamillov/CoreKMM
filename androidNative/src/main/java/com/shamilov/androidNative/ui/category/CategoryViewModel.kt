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
class CategoryViewModel(private val repository: RemoteRepository) : ViewModel() {

    private var _state = MutableStateFlow<CategoryState>(CategoryState.Loading)
    val state: StateFlow<CategoryState> = _state

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _state.value = repository.loadCategories()
                .fold(
                    { categories ->
                        if (categories.isNullOrEmpty()) {
                            CategoryState.IsEmpty
                        } else {
                            CategoryState.Success(categories)
                        }
                    },
                    { throwable ->
                        CategoryState.Error(throwable)
                    }
                )
        }
    }
}