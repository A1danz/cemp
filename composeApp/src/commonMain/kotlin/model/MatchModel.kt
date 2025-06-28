package model

import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.serialization.Serializable

@Serializable
data class MatchModel(
    val id: Int,
    val tournamentName: String,
    val status: StringDesc,
    val firstTeam: MatchTeamInfoModel,
    val secondTeam: MatchTeamInfoModel,
    val startDate: String?,
)
