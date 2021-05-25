package com.shamilov.core.repository

import com.shamilov.core.dispatcher.dispatcherUI
import com.shamilov.core.model.Response
import com.shamilov.core.model.response.CategoryResponse
import com.shamilov.core.model.response.ProductDetailResponse
import com.shamilov.core.model.response.ProductResponse
import com.shamilov.core.remote.KtorClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface RemoteRepository {
    fun loadCategories(callback: (Result<List<CategoryResponse>>) -> Unit)
    fun loadSubcategories(id: Int, callback: (Result<List<CategoryResponse>>) -> Unit)
    fun loadProducts(id: Int, callback: (Result<List<ProductResponse>>) -> Unit)
    fun loadProduct(id: Int, callback: (Result<List<ProductDetailResponse>>) -> Unit)
}

typealias Data<T> = Result<Response<T>>

class RemoteRepositoryImpl : RemoteRepository {

    //TODO("inject into constructor with DI")
    private val httpClient = KtorClient()

    override fun loadCategories(callback: (Result<List<CategoryResponse>>) -> Unit) {
        GlobalScope.launch(dispatcherUI) {
            try {
                val response = httpClient.loadData<Response<List<CategoryResponse>>>("/category")
                if (response != null) {
                    callback(Result.success(response.data))
                }
            } catch (exception: Throwable) {
                callback(Result.failure(exception))
            }
        }
    }

    override fun loadSubcategories(id: Int, callback: (Result<List<CategoryResponse>>) -> Unit) {
        GlobalScope.launch(dispatcherUI) {
            try {
                val response = httpClient.loadData<Response<List<CategoryResponse>>>("/category/$id")
                if (response != null) {
                    callback(Result.success(response.data))
                }
            } catch (exception: Throwable) {
                callback(Result.failure(exception))
            }
        }
    }

    override fun loadProducts(id: Int, callback: (Result<List<ProductResponse>>) -> Unit) {
        GlobalScope.launch(dispatcherUI) {
            try {
                val response = httpClient.loadData<Response<List<ProductResponse>>>("/category/$id/product")
                if (response != null) {
                    callback(Result.success(response.data))
                }
            } catch (exception: Throwable) {
                callback(Result.failure(exception))
            }
        }
    }

    override fun loadProduct(id: Int, callback: (Result<List<ProductDetailResponse>>) -> Unit) {
        GlobalScope.launch(dispatcherUI) {
            try {
                val response = httpClient.loadData<Response<List<ProductDetailResponse>>>("/product/$id")
                if (response != null) {
                    callback(Result.success(response.data))
                }
            } catch (exception: Throwable) {
                callback(Result.failure(exception))
            }
        }
    }
}