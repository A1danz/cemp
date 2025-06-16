package com.cemp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("location")
    val location: String? = null,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("players")
    val players: List<PlayerResponse>
)
