package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import component.LoginComponent

class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onLoginSuccess: () -> Unit
) : LoginComponent, ComponentContext by componentContext {

    private val state = MutableValue(LoginComponent.Model("", ""))
    override val model: Value<LoginComponent.Model> = state

    override fun onEmailChanged(value: String) {
        state.value = state.value.copy(email = value)
    }

    override fun onPasswordChanged(value: String) {
        state.value = state.value.copy(password = value)
    }

    override fun onLoginClick() {
//        if (state.value.email.isBlank() || state.value.password.isBlank()) {
//            state.value = state.value.copy(error = "Поля не должны быть пустыми")
//            return
//        }
//
//        state.value = state.value.copy(isLoading = true, error = null)
//
//        coroutineScope.launch {
//            delay(1000) // имитируем вход
//            state.value = state.value.copy(isLoading = false)
//            onLoginSuccess()
//        }
    }
}
