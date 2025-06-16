package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerieResponse(
    @SerialName("full_name")
    val fullName: String
)