package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.model.base.map
import com.cemp.feature.matches.domain.usecase.GetMatchesUseCase
import com.cemp.feature.teams.domain.usecase.GetTeamUseCase
import component.MatchDetailsComponent
import component.TeamDetailsComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import mapper.toUi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultTeamDetailsComponent(
    componentContext: ComponentContext,
    val teamId: Int,
    private val onBack: () -> Unit,
) : TeamDetailsComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(TeamDetailsComponent.Model.createInitialValue())
    override val state: Value<TeamDetailsComponent.Model> = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val getMatchesUseCase by inject<GetMatchesUseCase>()
    private val getTeamUseCase by inject<GetTeamUseCase>()

    init {
        loadMatches()
    }

    override fun onIntent(intent: TeamDetailsComponent.Intent) {
        when (intent) {
            TeamDetailsComponent.Intent.BackClicked -> onBack()
        }
    }

    private fun loadMatches() {
        scope.launch {
            _state.value = state.value.copy(isLoading = true, isError = false)

            val teamDeferred = async {
                runCatching {
                    getTeamUseCase(teamId)
                }.getOrNull()
            }
            val matchesDeferred = async {
                runCatching {
                    getMatchesUseCase(teamId)
                }.getOrNull()
            }

            val team = teamDeferred.await() as? NetworkResponse.Success
            val matches = matchesDeferred.await() as? NetworkResponse.Success

            if (team?.data == null || matches == null) {
                _state.value = state.value.copy(isLoading = false, isError = true)
                return@launch
            }

            _state.value = state.value.copy(
                isLoading = false,
                team = team.data!!.toUi(),
                recentMatches = matches.data.map { it.toUi() }
            )
        }
    }


}