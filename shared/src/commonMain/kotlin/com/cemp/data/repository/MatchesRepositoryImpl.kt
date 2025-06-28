package com.cemp.data.repository

import com.cemp.data.mapper.toMatch
import com.cemp.data.network.makeSafeApiCall
import com.cemp.data.network.response.MatchResponse
import com.cemp.domain.model.Match
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.model.base.map
import com.cemp.domain.repository.BaseRepository.Companion.EMPTY_REQUEST_LOGIC
import com.cemp.domain.repository.MatchesRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MatchesRepositoryImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher,
) : MatchesRepository {

    override suspend fun getMatches(): NetworkResponse<List<Match>> {
        return getMatches(EMPTY_REQUEST_LOGIC)
    }

    override suspend fun getMatches(teamId: Int): NetworkResponse<List<Match>> {
        return getMatches {
            url {
                parameters.append("filter[opponent_id]", teamId.toString())
                parameters.append("filter[winner_type]", "Team")
            }
        }
    }

    private suspend fun getMatches(requestLogic: HttpRequestBuilder.() -> Unit): NetworkResponse<List<Match>> {
        return withContext(dispatcher) {
            makeSafeApiCall<List<MatchResponse>> {
                httpClient.get("/csgo/matches") {
                    apply(requestLogic)
                }
            }.let { response ->
                response.map { matchesList ->
                    matchesList.mapNotNull { matchResponse ->
                        matchResponse.toMatch()
                    }
                }
            }
        }
    }
}