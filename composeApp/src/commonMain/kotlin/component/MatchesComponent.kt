package component

import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.Match
import model.MatchModel

interface MatchesComponent {

    val state: Value<Model>

    fun onIntent(matchesIntent: Intent)

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val matches: List<MatchModel>,
    ) {
        companion object {
            fun createInitialValue() = Model(
                isLoading = true,
                isError = false,
                matches = emptyList(),
            )
        }
    }

    sealed interface Intent {
        data object OnLogoutClicked : Intent
        data class OnMatchClicked(val model: MatchModel) : Intent
    }
}