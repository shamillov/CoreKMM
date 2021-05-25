package com.shamilov.core.model

import kotlinx.serialization.Serializable

/**
 * Created by Shamilov on 22.05.2021
 */
@Serializable
data class Response<T>(val data: T)