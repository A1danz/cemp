package component.impl

import com.arkivanov.decompose.ComponentContext
import component.WelcomeComponent

class DefaultWelcomeComponent(
    componentContext: ComponentContext,
    private val onLoginClick: () -> Unit,
    private val onRegisterClick: () -> Unit,
) : WelcomeComponent, ComponentContext by componentContext {
    override fun onLoginClick() = onLoginClick.invoke()
    override fun onRegisterClick() = onRegisterClick.invoke()
}