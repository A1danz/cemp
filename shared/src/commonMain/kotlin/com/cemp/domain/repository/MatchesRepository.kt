package com.cemp.domain.repository

import com.cemp.domain.model.Match
import com.cemp.domain.model.base.NetworkResponse

interface MatchesRepository : BaseRepository {
    suspend fun getMatches(): NetworkResponse<List<Match>>
    suspend fun getMatches(teamId: Int): NetworkResponse<List<Match>>
}