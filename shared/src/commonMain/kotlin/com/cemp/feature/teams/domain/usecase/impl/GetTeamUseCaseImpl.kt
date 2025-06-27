package com.cemp.feature.teams.domain.usecase.impl

import com.cemp.domain.model.Team
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.repository.TeamsRepository
import com.cemp.feature.teams.domain.usecase.GetTeamUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetTeamUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val teamsRepository: TeamsRepository,
) : GetTeamUseCase {
    override suspend operator fun invoke(teamId: Int): NetworkResponse<Team?> {
        return withContext(dispatcher) {
            teamsRepository.getTeam(teamId)
        }
    }
}