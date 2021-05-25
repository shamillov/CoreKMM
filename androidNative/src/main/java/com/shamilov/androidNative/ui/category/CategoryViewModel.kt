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
            when {
                result.isSuccess -> _state.value = CategoryState.Success(result.getOrThrow())
                result.isFailure -> _state.value = CategoryState.Error(result.exceptionOrNull())
            }
        }
    }
}