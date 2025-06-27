package component

import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.Team
import model.MatchModel
import model.TeamModel

interface TeamDetailsComponent {
    val state: Value<Model>
    
    fun onIntent(intent: Intent)

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val team: TeamModel?,
        val recentMatches: List<MatchModel>,
    ) {
        companion object {
            fun createInitialValue(): Model {
                return Model(
                    isLoading = true,
                    isError = false,
                    team = null,
                    recentMatches = listOf()
                )
            }
        }
    }

    sealed interface Intent {
        data object BackClicked : Intent
    }
}