package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import component.RegisterComponent

class DefaultRegisterComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onRegisterSuccess: () -> Unit
) : RegisterComponent, ComponentContext by componentContext {

    private val state = MutableValue(RegisterComponent.Model("", "", "", "", ""))
    override val model: Value<RegisterComponent.Model> = state

    override fun onEmailChanged(value: String) {
        state.value = state.value.copy(email = value)
    }

    override fun onPasswordChanged(value: String) {
        state.value = state.value.copy(password = value)
    }

    override fun onConfirmPasswordChanged(value: String) {
        state.value = state.value.copy(confirmPassword = value)
    }

    override fun onNameChanged(value: String) {
        state.value = state.value.copy(name = value)
    }

    override fun onUsernameChanged(value: String) {
        state.value = state.value.copy(username = value)
    }

    override fun onRegisterClick() {
//        if (state.value.email.isBlank() || state.value.password.isBlank()) {
//            state.value = state.value.copy(error = "Все поля обязательны")
//            return
//        }
//
//        state.value = state.value.copy(isLoading = true, error = null)
//
//        coroutineScope.launch {
//            delay(1500) // имитируем регистрацию
//            state.value = state.value.copy(isLoading = false)
//            onRegisterSuccess()
//        }
    }
}
