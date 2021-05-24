package com.shamilov.core.remote

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class KtorClient {
    companion object {
        const val BASE_URL = "http://uvays.fvds.ru/api"
    }

    val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend inline fun <reified T> loadData(url: String): T? {
        return httpClient.get(BASE_URL + url)
    }
}