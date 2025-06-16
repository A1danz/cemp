package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueResponse(
    @SerialName("name")
    val name: String,
)