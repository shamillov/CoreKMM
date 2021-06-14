package com.shamilov.core.remote

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

/**
 * Created by Shamilov on 21.05.2021
 */
object KtorClient {
    private const val BASE_URL = "uvays.fvds.ru"

    internal val http = HttpClient {
        install(JsonFeature) {
            val json = Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            host = BASE_URL
            url {
                protocol = URLProtocol.HTTP
            }
        }
    }

    internal suspend inline fun <reified T> get(url: String): T? {
        return http.get {
            url { path(url) }
        }
    }
}