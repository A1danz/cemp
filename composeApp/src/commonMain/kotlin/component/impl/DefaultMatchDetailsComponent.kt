package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.feature.teams.domain.usecase.GetTeamUseCase
import component.MatchDetailsComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import mapper.toUi
import model.MatchModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultMatchDetailsComponent(
    componentContext: ComponentContext,
    private val match: MatchModel,
) : MatchDetailsComponent, ComponentContext by componentContext, KoinComponent {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val getTeamUseCase by inject<GetTeamUseCase>()

    private val _state = MutableValue(MatchDetailsComponent.Model.createInitialValue())
    override val state: Value<MatchDetailsComponent.Model> = _state

    init {
        loadRosters()
    }

    private fun loadRosters() {
        scope.launch {
            _state.value = state.value.copy(isLoading = true)

            val firstTeamDeferred = async {
                kotlin.runCatching {
                    getTeamUseCase.invoke(match.firstTeam.id)
                }.getOrNull()
            }
            val secondTeamDeferred = async {
                kotlin.runCatching {
                    getTeamUseCase.invoke(match.secondTeam.id)
                }.getOrNull()
            }

            val firstTeamResult = firstTeamDeferred.await()
            val secondTeamResult = secondTeamDeferred.await()

            val firstTeam = firstTeamResult as? NetworkResponse.Success
            val secondTeam = secondTeamResult as? NetworkResponse.Success

            if (firstTeam?.data == null || secondTeam?.data == null) {
                _state.value = state.value.copy(isError = true, isLoading = false)
                return@launch
            }

            _state.value = state.value.copy(
                isLoading = false,
                isError = false,
                match = match,
                firstTeamPlayers = firstTeam.data!!.roster.map { it.toUi() },
                secondTeamsPlayers = secondTeam.data!!.roster.map { it.toUi() },
            )
        }
    }
}