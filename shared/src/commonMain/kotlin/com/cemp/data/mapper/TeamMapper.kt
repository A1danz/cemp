package com.cemp.data.mapper

import com.cemp.data.network.response.TeamInMatchResponse
import com.cemp.data.network.response.TeamResponse
import com.cemp.domain.model.Country
import com.cemp.domain.model.Team
import com.cemp.domain.model.TeamInMatch

fun TeamInMatchResponse.toTeamInMatch(): TeamInMatch {
    return TeamInMatch(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

fun TeamResponse.toTeam(): Team {
    return Team(
        id = id,
        name = name,
        imageUrl = imageUrl,
        roster = players.map { it.toPlayer() },
        location = location?.let { Country.getByCountryCode(it) }
    )
}