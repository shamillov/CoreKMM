package com.shamilov.core.repository

import com.shamilov.core.dispatcher.dispatcherUI
import com.shamilov.core.model.Response
import com.shamilov.core.model.response.CategoryResponse
import com.shamilov.core.remote.KtorClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface RemoteRepository {
    fun loadCategories(callback: (Data<List<CategoryResponse>>) -> Unit)
    fun loadSubcategories()
    fun loadProducts()
    fun loadProduct()
}

typealias Data<T> = Result<Response<T>>

class RemoteRepositoryImpl : RemoteRepository {

    //TODO("inject into constructor with DI")
    private val httpClient = KtorClient()

    override fun loadCategories(callback: (Data<List<CategoryResponse>>) -> Unit) {
        GlobalScope.launch(dispatcherUI) {
            try {
                val response = httpClient.loadData<Response<List<CategoryResponse>>>("/category")
                if (response != null) {
                    callback(Result.success(response))
                }
            } catch (exception: Throwable) {
                callback(Result.failure(exception))
            }
        }
    }

    override fun loadSubcategories() {
        TODO("Not yet implemented")
    }

    override fun loadProducts() {
        TODO("Not yet implemented")
    }

    override fun loadProduct() {
        TODO("Not yet implemented")
    }
}