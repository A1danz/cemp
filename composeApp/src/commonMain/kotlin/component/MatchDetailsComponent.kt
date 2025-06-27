package component

import com.arkivanov.decompose.value.Value
import com.cemp.domain.model.Player
import model.MatchModel
import model.PlayerModel

interface MatchDetailsComponent {
    val state: Value<Model>

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val match: MatchModel?,
        val firstTeamPlayers: List<PlayerModel>,
        val secondTeamsPlayers: List<PlayerModel>,
    ) {
        companion object {
            fun createInitialValue(): Model {
                return Model(
                    isLoading = true,
                    isError = false,
                    match = null,
                    firstTeamPlayers = listOf(),
                    secondTeamsPlayers = listOf(),
                )
            }
        }
    }
}