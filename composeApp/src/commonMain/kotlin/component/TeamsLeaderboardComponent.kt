package component

import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.Team
import model.TeamModel

interface TeamsLeaderboardComponent {

    val state: Value<Model>

    fun onIntent(intent: Intent)

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val teams: List<TeamModel>
    ) {
        companion object {
            fun createInitialValue() = Model(
                isLoading = true,
                isError = false,
                teams = emptyList(),
            )
        }
    }

    sealed interface Intent {
        data class OnTeamClicked(val teamId: Int) : Intent
    }
}