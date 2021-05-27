package com.shamilov.androidNative.ui.category

import androidx.lifecycle.ViewModel
import com.shamilov.core.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Shamilov on 24.05.2021
 */
class CategoryViewModel : ViewModel() {

    private val repository = RemoteRepositoryImpl()

    private var _state = MutableStateFlow<CategoryState>(CategoryState.Loading)
    val state: StateFlow<CategoryState> = _state

    init {
        loadCategories()
    }

    private fun loadCategories() {
        repository.loadCategories { result ->
            result.fold(
                { categories ->
                    if (categories.isNullOrEmpty()) {
                        _state.value = CategoryState.IsEmpty
                    } else {
                        _state.value = CategoryState.Success(categories)
                    }
                },
                { throwable -> _state.value = CategoryState.Error(throwable) }
            )
        }
    }
}