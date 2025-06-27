package component.impl

import com.arkivanov.decompose.ComponentContext
import component.TeamsLeaderboardComponent

class DefaultTeamsLeaderboardComponent(
    componentContext: ComponentContext,
) : TeamsLeaderboardComponent, ComponentContext by componentContext {
}