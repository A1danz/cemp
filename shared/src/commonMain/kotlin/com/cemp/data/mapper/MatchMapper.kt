package com.cemp.data.mapper

import com.cemp.data.network.response.MatchResponse
import com.cemp.domain.model.Match
import com.cemp.domain.model.MatchStatus
import com.cemp.domain.model.MatchTeamInfo

fun MatchResponse.toMatch(): Match? {
    if (opponents.size != 2) return null

    return Match(
        id = id,
        tournamentName = "${league.name} ${serie.fullName}",
        status = status.toMatchStatus(),
        firstTeamInfo = getTeamInfo(opponents[0].opponent.id),
        secondTeamInfo = getTeamInfo(opponents[1].opponent.id),
        startDate = beginAt,
    )
}

fun MatchResponse.getTeamInfo(teamId: Int): MatchTeamInfo {
    return MatchTeamInfo(
        teamInMatch = opponents.first { it.opponent.id == teamId }.opponent.toTeamInMatch(),
        score = results.first { it.teamId == teamId }.score
    )
}


fun String.toMatchStatus(): MatchStatus {
    return when (this) {
        "canceled" -> MatchStatus.CANCELLED
        "finished" -> MatchStatus.FINISHED
        "not_started" -> MatchStatus.NOT_STARTED
        "postponed" -> MatchStatus.POSTPONED
        "running" -> MatchStatus.RUNNING
        else -> throw IllegalArgumentException("Unknown enum for match status: $this")
    }
}