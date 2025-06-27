package com.cemp.data.network.response

import com.cemp.data.serializer.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponse(
    @SerialName("begin_at")
    @Serializable(with = InstantSerializer::class)
    val beginAt: Instant?,
    @SerialName("id")
    val id: Int,
    @SerialName("status")
    val status: String,
    @SerialName("serie")
    val serie: SerieResponse,
    @SerialName("league")
    val league: LeagueResponse,
    @SerialName("opponents")
    val opponents: List<OpponentResponse>,
    @SerialName("games")
    val games: List<GameResponse>,
    @SerialName("results")
    val results: List<ResultResponse>
)

