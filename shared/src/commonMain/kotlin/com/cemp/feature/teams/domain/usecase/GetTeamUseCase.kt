package com.cemp.feature.teams.domain.usecase

import com.cemp.domain.model.Team
import com.cemp.domain.model.base.NetworkResponse

interface GetTeamUseCase {
    suspend operator fun invoke(teamId: Int): NetworkResponse<Team?>
}