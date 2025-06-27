package model

import kotlinx.serialization.Serializable

@Serializable
data class MatchTeamInfoModel(
    val id: Int,
    val score: Int,
    val name: String,
    val imageUrl: String?,
)