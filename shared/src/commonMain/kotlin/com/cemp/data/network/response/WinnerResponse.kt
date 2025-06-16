package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WinnerResponse(
    @SerialName("id")
    val id: Long? = null
)