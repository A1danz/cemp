package com.cemp.domain.repository

import com.cemp.domain.model.Team
import com.cemp.domain.model.base.NetworkResponse

interface TeamsRepository : BaseRepository {
    suspend fun getTeams(): NetworkResponse<List<Team>>
    suspend fun getTeam(teamId: Int): NetworkResponse<Team?>
}