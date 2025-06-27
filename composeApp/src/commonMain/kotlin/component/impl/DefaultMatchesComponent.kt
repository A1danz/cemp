package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.cemp.common.ext.logErr
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.feature.auth.domain.usecase.LogoutUseCase
import com.cemp.feature.matches.domain.usecase.GetMatchesUseCase
import component.MatchesComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mapper.toUi
import model.MatchModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.GlobalEvent
import utils.GlobalEvents

class DefaultMatchesComponent(
    componentContext: ComponentContext,
    private val onMatchClick: (MatchModel) -> Unit,
) : MatchesComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(MatchesComponent.Model.createInitialValue())
    override val state: Value<MatchesComponent.Model> = _state

    private val getMatchesUseCase: GetMatchesUseCase by inject()
    private val logoutUseCase: LogoutUseCase by inject()
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        loadMatches()
    }

    override fun onIntent(matchesIntent: MatchesComponent.Intent) {
        when (matchesIntent) {
            MatchesComponent.Intent.OnLogoutClicked -> onLogoutClicked()
            is MatchesComponent.Intent.OnMatchClicked -> onMatchClick(matchesIntent.model)
        }
    }

    private fun loadMatches() {
        scope.launch {
            _state.value = state.value.copy(isLoading = true, isError = false)

            kotlin.runCatching {
                getMatchesUseCase()
            }.onSuccess { result ->
                _state.value = when (result) {
                    is NetworkResponse.Error -> {
                        state.value.copy(isError = true)
                    }

                    is NetworkResponse.Success -> {
                        state.value.copy(matches = result.data.map { it.toUi() })
                    }
                }.copy(isLoading = false)

//                delay(7000)
//                val data = (result as NetworkResponse.Success).data
//                onIntent(MatchesComponent.Intent.OnMatchClicked(data.first().toUi()))

            }.onFailure {
                _state.value = state.value.copy(isLoading = false, isError = true)
            }
        }
    }

    private fun onLogoutClicked() {
        scope.launch {
            kotlin.runCatching {
                logoutUseCase.invoke()
            }.onSuccess {
                GlobalEvents.send(GlobalEvent.NavigateToLogin)
            }
        }
    }
}