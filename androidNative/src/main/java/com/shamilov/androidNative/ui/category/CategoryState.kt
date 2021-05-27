package com.shamilov.androidNative.ui.category

import com.shamilov.core.model.response.CategoryResponse

/**
 * Created by Shamilov on 25.05.2021
 */
sealed class CategoryState {
    class Success(val data: List<CategoryResponse>) : CategoryState()
    class Error(val exception: Throwable) : CategoryState()
    object Loading : CategoryState()
    object IsEmpty : CategoryState()
}