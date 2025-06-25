package component.impl

import com.arkivanov.decompose.ComponentContext
import component.WelcomeComponent

class DefaultWelcomeComponent(
    componentContext: ComponentContext,
    private val onLoginClick: () -> Unit,
    private val onRegisterClick: () -> Unit,
) : WelcomeComponent, ComponentContext by componentContext {

    override fun onIntent(intent: WelcomeComponent.Intent) {
        when(intent) {
            WelcomeComponent.Intent.LoginClicked -> onLoginClick()
            WelcomeComponent.Intent.RegisterClicked -> onRegisterClick()
        }
    }
}