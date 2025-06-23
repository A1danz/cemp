package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import component.RootComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Auth,
        childFactory = ::createChild,
        serializer = Config.serializer(),
    )

    private fun createChild(config: Config, ctx: ComponentContext): RootComponent.Child =
        when (config) {
            Config.Auth -> RootComponent.Child.Auth(
                DefaultAuthComponent(
                    componentContext = ctx,
                    onAuthSuccess = {}
                )
            )
        }

    @Serializable
    sealed class Config {
        @Serializable
        data object Auth : Config()
    }
}
