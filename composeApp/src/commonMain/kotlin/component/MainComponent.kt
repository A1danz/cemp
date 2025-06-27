package component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.MatchModel

interface MainComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onTabSelected(tab: Tab)

    @Serializable
    sealed interface Tab {
        @Serializable
        data object Matches : Tab

        @Serializable
        data object TeamsLeaderboard : Tab

        @Serializable
        data class TeamDetails(val teamId: Int) : Tab

        @Serializable
        data class MatchDetails(val match: MatchModel) : Tab
    }

    sealed class Child {
        class Matches(val component: MatchesComponent) : Child()
        class TeamsLeaderboard(val component: TeamsLeaderboardComponent) : Child()
        class TeamDetails(val component: TeamDetailsComponent) : Child()
        class MatchDetails(val component: MatchDetailsComponent) : Child()
    }

    sealed interface Intent {
        data object OnLogoutClicked : Intent
    }
}
