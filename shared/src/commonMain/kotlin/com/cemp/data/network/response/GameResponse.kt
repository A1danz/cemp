package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("status")
    val status: String,
    @SerialName("position")
    val position: Int,
    @SerialName("winner")
    val winner: WinnerResponse? = null
)