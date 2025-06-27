package com.cemp.feature.matches.domain.usecase.impl

import com.cemp.domain.model.Match
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.repository.MatchesRepository
import com.cemp.feature.matches.domain.usecase.GetMatchesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetMatchesUseCaseImpl(
    private val matchesRepository: MatchesRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetMatchesUseCase {
    override suspend operator fun invoke(teamId: Int?): NetworkResponse<List<Match>> {
        return withContext(dispatcher) {
            if (teamId != null) {
                matchesRepository.getMatches(teamId)
            } else {
                matchesRepository.getMatches()
            }
        }
    }
}