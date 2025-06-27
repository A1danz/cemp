package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import component.MainComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent

class DefaultMainComponent(
    componentContext: ComponentContext,
) : MainComponent, ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<MainComponent.Tab>()
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        navigation.subscribe {
            it.onComplete
        }
    }

    override val childStack: Value<ChildStack<MainComponent.Tab, MainComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = MainComponent.Tab.Matches,
            handleBackButton = true,
            childFactory = ::createChild,
            serializer = MainComponent.Tab.serializer(),
        )

    private fun createChild(
        tab: MainComponent.Tab,
        context: ComponentContext
    ): MainComponent.Child =
        when (tab) {
            MainComponent.Tab.Matches -> MainComponent.Child.Matches(
                DefaultMatchesComponent(
                    componentContext = context,
                    onMatchClick = { navigation.push(MainComponent.Tab.MatchDetails(it)) },
                ),
            )
            MainComponent.Tab.TeamsLeaderboard -> MainComponent.Child.TeamsLeaderboard(
                DefaultTeamsLeaderboardComponent(
                    componentContext = context,
                    onTeamClicked = { navigation.push(MainComponent.Tab.TeamDetails(it)) })
            )

            is MainComponent.Tab.MatchDetails -> MainComponent.Child.MatchDetails(
                DefaultMatchDetailsComponent(
                    componentContext = context,
                    match = tab.match,
                    onTeamClicked = { navigation.push(MainComponent.Tab.TeamDetails(it)) },
                    onBack = { navigation.pop() },
                )
            )
            is MainComponent.Tab.TeamDetails -> MainComponent.Child.TeamDetails(
                DefaultTeamDetailsComponent(
                    componentContext = context,
                    teamId = tab.teamId,
                )
            )
        }

    override fun onTabSelected(tab: MainComponent.Tab) {
        val currentTab = childStack.value.active.configuration
        if (currentTab != tab) {
            navigation.bringToFront(tab)
        }
    }
}
