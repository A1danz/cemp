package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("score")
    val score: Int,
    @SerialName("team_id")
    val teamId: Int,
)