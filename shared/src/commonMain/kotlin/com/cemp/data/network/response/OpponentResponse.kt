package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpponentResponse(
    @SerialName("opponent")
    val opponent: TeamInMatchResponse
)