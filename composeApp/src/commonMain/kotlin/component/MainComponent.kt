package component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface MainComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onTabSelected(tab: Tab)

    @Serializable
    sealed interface Tab {
        @Serializable
        data object Matches : Tab

        @Serializable
        data object TeamsLeaderboard : Tab
    }

    sealed class Child {
        class Matches(val component: MatchesComponent) : Child()
        class TeamsLeaderboard(val component: TeamsLeaderboardComponent) : Child()
    }

    sealed interface Intent {
        data object OnLogoutClicked : Intent
    }
}
