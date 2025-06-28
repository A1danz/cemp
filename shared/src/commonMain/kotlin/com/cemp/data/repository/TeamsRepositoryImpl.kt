package com.cemp.data.repository

import com.cemp.data.mapper.toTeam
import com.cemp.data.network.makeSafeApiCall
import com.cemp.data.network.response.TeamResponse
import com.cemp.domain.model.Team
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.model.base.map
import com.cemp.domain.repository.BaseRepository.Companion.EMPTY_REQUEST_LOGIC
import com.cemp.domain.repository.TeamsRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TeamsRepositoryImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher,
) : TeamsRepository {
    override suspend fun getTeams(): NetworkResponse<List<Team>> {
        return getTeams(EMPTY_REQUEST_LOGIC)
    }

    override suspend fun getTeam(teamId: Int): NetworkResponse<Team?> {
        return getTeams {
            url {
                parameters.append("filter[id]", teamId.toString())
            }
        }.map { response ->
            response.firstOrNull()
        }
    }

    private suspend fun getTeams(requestLogic: HttpRequestBuilder.() -> Unit): NetworkResponse<List<Team>> {
        return withContext(dispatcher) {
            makeSafeApiCall<List<TeamResponse>> {
                httpClient.get("/csgo/teams") {
                    apply(requestLogic)
                }
            }.map { response ->
                response.map { teamResponse -> teamResponse.toTeam() }
            }
        }
    }
}