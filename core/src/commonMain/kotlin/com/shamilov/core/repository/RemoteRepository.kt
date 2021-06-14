package com.shamilov.core.repository

import com.shamilov.core.model.Response
import com.shamilov.core.model.response.CategoryResponse
import com.shamilov.core.model.response.ProductDetailResponse
import com.shamilov.core.model.response.ProductResponse
import com.shamilov.core.remote.KtorClient
import io.ktor.client.request.get

/**
 * Created by Shamilov on 14.06.2021
 */
interface RemoteRepository {
    suspend fun loadCategories(): Result<List<CategoryResponse>>
    suspend fun loadSubcategories(id: Int, callback: (Result<List<CategoryResponse>>) -> Unit)
    suspend fun loadProducts(id: Int): Result<List<ProductResponse>>
    suspend fun loadProduct(id: Int, callback: (Result<List<ProductDetailResponse>>) -> Unit)
}

typealias Data<T> = Result<Response<T>>

class RemoteRepositoryImpl(private val client: KtorClient) : RemoteRepository {

    override suspend fun loadCategories(): Result<List<CategoryResponse>> {
        return try {
            val response = client.http.get<Response<List<CategoryResponse>>> {
                url { path("api/category") }
            }
            Result.success(response.data)
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun loadSubcategories(id: Int, callback: (Result<List<CategoryResponse>>) -> Unit) {
        try {
            val response = client.http.get<Response<List<CategoryResponse>>> {
                url { path("api/category/$id") }
            }
            callback(Result.success(response.data))
        } catch (exception: Throwable) {
            callback(Result.failure(exception))
        }
    }

    override suspend fun loadProducts(id: Int): Result<List<ProductResponse>> {
        return try {
            val response = client.http.get<Response<List<ProductResponse>>> {
                url { path("api/category/$id/product") }
            }
            Result.success(response.data)
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun loadProduct(id: Int, callback: (Result<List<ProductDetailResponse>>) -> Unit) {
        try {
            val response = client.http.get<Response<List<ProductDetailResponse>>> {
                url { path("api/product/$id") }
            }
            callback(Result.success(response.data))
        } catch (exception: Throwable) {
            callback(Result.failure(exception))
        }
    }
}