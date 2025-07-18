package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import component.AuthComponent
import kotlinx.serialization.Serializable
import utils.enableAnalytics

class DefaultAuthComponent(
    componentContext: ComponentContext,
    private val onAuthSuccess: () -> Unit
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, AuthComponent.Child>> = childStack(
        source = navigation,
        childFactory = ::createChild,
        handleBackButton = true,
        initialStack = { listOf(Config.Welcome) },
        serializer = Config.serializer(),
    )

    init {
        childStack.enableAnalytics()
    }

    private fun createChild(config: Config, ctx: ComponentContext): AuthComponent.Child =
        when (config) {
            Config.Welcome -> AuthComponent.Child.Welcome(
                DefaultWelcomeComponent(
                    ctx,
                    onLoginClick = {
                        navigation.push(Config.Login)
                    },
                    onRegisterClick = { navigation.push(Config.Register) }
                )
            )

            Config.Login -> AuthComponent.Child.Login(
                DefaultLoginComponent(
                    ctx,
                    onBack = { navigation.pop() },
                    onLoginSuccess = { onAuthSuccess() }
                )
            )

            Config.Register -> AuthComponent.Child.Register(
                DefaultRegisterComponent(
                    ctx,
                    onBack = { navigation.pop() },
                    onRegisterSuccess = { onAuthSuccess() }
                )
            )
        }

    @Serializable
    private sealed class Config {
        @Serializable
        data object Welcome : Config()

        @Serializable
        data object Login : Config()

        @Serializable
        data object Register : Config()
    }
}
