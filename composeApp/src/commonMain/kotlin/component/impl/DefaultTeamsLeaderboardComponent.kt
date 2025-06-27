package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.feature.leaderboard.domain.usecase.GetTeamsLeaderboardUseCase
import component.TeamsLeaderboardComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mapper.toUi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultTeamsLeaderboardComponent(
    componentContext: ComponentContext,
    private val onTeamClicked: (Int) -> Unit,
) : TeamsLeaderboardComponent, ComponentContext by componentContext, KoinComponent {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val getTeamsLeaderBoardUseCase: GetTeamsLeaderboardUseCase by inject()

    private val _state = MutableValue(TeamsLeaderboardComponent.Model.createInitialValue())
    override val state: Value<TeamsLeaderboardComponent.Model> = _state

    init {
        loadTeamsLeaderboard()
    }

    override fun onIntent(intent: TeamsLeaderboardComponent.Intent) {
        when(intent) {
            is TeamsLeaderboardComponent.Intent.OnTeamClicked -> onTeamClicked(intent.teamId)
        }
    }

    private fun onTeamClicked(teamId: Int) {
        onTeamClicked.invoke(teamId)
    }

    private fun loadTeamsLeaderboard() {
        scope.launch {
            _state.value = state.value.copy(isLoading = true, isError = false)

            kotlin.runCatching {
                getTeamsLeaderBoardUseCase()
            }.onSuccess { result ->
                _state.value = when(result) {
                    is NetworkResponse.Error -> state.value.copy(isError = true)
                    is NetworkResponse.Success -> state.value.copy(
                        teams = result.data.map { it.toUi() },
                    )
                }.copy(isLoading = false)

                val data = (result as NetworkResponse.Success).data

                delay(7000)
                onTeamClicked(data.first().id)
            }.onFailure {
                _state.value = state.value.copy(isLoading = false, isError = true)
            }
        }
    }
}