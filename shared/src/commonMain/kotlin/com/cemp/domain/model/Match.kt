package com.cemp.domain.model

import kotlinx.datetime.Instant

data class Match(
    val id: Int,
    val tournamentName: String,
    val status: MatchStatus,
    val firstTeamInfo: MatchTeamInfo,
    val secondTeamInfo: MatchTeamInfo,
    val startDate: Instant?,
)