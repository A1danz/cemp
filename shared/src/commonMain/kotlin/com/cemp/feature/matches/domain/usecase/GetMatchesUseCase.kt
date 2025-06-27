package com.cemp.feature.matches.domain.usecase

import com.cemp.domain.model.Match
import com.cemp.domain.model.base.NetworkResponse

interface GetMatchesUseCase {
    suspend operator fun invoke(teamId: Int? = null): NetworkResponse<List<Match>>
}