package com.shamilov.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(val data: T)