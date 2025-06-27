package com.cemp.feature.leaderboard.domain.usecase

import com.cemp.domain.model.Team
import com.cemp.domain.model.base.NetworkResponse

interface GetTeamsLeaderboardUseCase {
    suspend operator fun invoke(): NetworkResponse<List<Team>>
}