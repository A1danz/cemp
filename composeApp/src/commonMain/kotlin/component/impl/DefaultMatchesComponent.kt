package component.impl

import com.arkivanov.decompose.ComponentContext
import component.MatchesComponent

class DefaultMatchesComponent(
    componentContext: ComponentContext,
) : MatchesComponent, ComponentContext by componentContext {
}