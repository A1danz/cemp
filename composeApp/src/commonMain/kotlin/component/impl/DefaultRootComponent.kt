package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.subscribe
import com.cemp.domain.repository.UserLocalRepository
import component.RootComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.GlobalEvent
import utils.GlobalEvents


class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Config>()

    private val userLocalRepo by inject<UserLocalRepository>()

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = chooseInitialScreenByAuth(),
        childFactory = ::createChild,
        serializer = Config.serializer(),
    )

    private val globalEventsObservingJob: Job? = null

    init {
        lifecycle.subscribe(
            onCreate = {
                scope.launch {
                    GlobalEvents.channel.collect(::collectGlobalEvent)
                }
            },
        )
    }

    private fun chooseInitialScreenByAuth(): Config {
        return if (userLocalRepo.getUser() != null) {
            Config.Main
        } else {
            Config.Auth
        }
    }

    private fun createChild(config: Config, ctx: ComponentContext): RootComponent.Child =
        when (config) {
            Config.Auth -> RootComponent.Child.Auth(
                DefaultAuthComponent(
                    componentContext = ctx,
                    onAuthSuccess = {
                        navigation.replaceAll(Config.Main)
                    }
                )
            )

            Config.Main -> RootComponent.Child.Main(
                DefaultMainComponent(
                    componentContext = ctx,
                )
            )
        }

    private fun collectGlobalEvent(event: GlobalEvent) {
        when (event) {
            GlobalEvent.NavigateToLogin -> navigation.replaceAll(Config.Auth)
        }
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object Auth : Config()

        @Serializable
        data object Main : Config()
    }
}
