package com.shamilov.core.repository

import com.shamilov.core.remote.KtorClient

interface RemoteRepository {
    fun loadCategories()
    fun loadSubcategories()
    fun loadProducts()
    fun loadProduct()
}

class RemoteRepositoryImpl : RemoteRepository {

    //TODO("inject into constructor with DI")
    val httpClient = KtorClient()

    override fun loadCategories() {
        TODO("Not yet implemented")
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