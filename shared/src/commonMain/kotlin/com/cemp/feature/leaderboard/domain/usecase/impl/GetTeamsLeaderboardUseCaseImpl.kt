package com.cemp.feature.leaderboard.domain.usecase.impl

import com.cemp.domain.model.Team
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.repository.TeamsRepository
import com.cemp.feature.leaderboard.domain.usecase.GetTeamsLeaderboardUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetTeamsLeaderboardUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val teamsRepository: TeamsRepository,
) : GetTeamsLeaderboardUseCase {
    override suspend operator fun invoke(): NetworkResponse<List<Team>> {
        return withContext(dispatcher) {
            teamsRepository.getTeams()
        }
    }
}