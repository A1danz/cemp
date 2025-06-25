package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.cemp.feature.auth.domain.model.error.LoginError
import com.cemp.feature.auth.domain.model.result.LoginResult
import com.cemp.feature.auth.domain.usecase.LoginUseCase
import component.LoginComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onLoginSuccess: () -> Unit
) : LoginComponent, ComponentContext by componentContext, KoinComponent {

    private val state = MutableValue(LoginComponent.Model.createInitialValue())
    override val model: Value<LoginComponent.Model> = state

    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val loginUseCase by inject<LoginUseCase>()

    override fun onIntent(intent: LoginComponent.Intent) {
        when (intent) {
            is LoginComponent.Intent.EmailChanged -> onEmailChanged(intent.value)
            LoginComponent.Intent.LoginClicked -> onLoginClicked()
            is LoginComponent.Intent.PasswordChanged -> onPasswordChanged(intent.value)
        }
    }

    private fun onEmailChanged(value: String) {
        state.value = state.value.copy(email = value, emailError = "")
    }

    private fun onPasswordChanged(value: String) {
        state.value = state.value.copy(password = value, passwordError = "")
    }

    private fun onLoginClicked() {
        if (state.value.email.isBlank() || state.value.password.isBlank()) {
            state.value = state.value.copy(globalError = "Поля не должны быть пустыми")
            return
        }

        scope.launch {
            state.value = state.value.copy(isLoading = true)

            runCatching {
                loginUseCase(state.value.email, state.value.password)
            }.onSuccess { result ->
                when (result) {
                    is LoginResult.Failed -> handleFailedResult(result.error)
                    LoginResult.Success -> onLoginSuccess()
                }
                onLoginSuccess()
            }.onFailure {
                state.value = state.value.copy(
                    isLoading = false,
                    globalError = "Произошла незивестная ошибка"
                )
            }
        }
    }

    private fun handleFailedResult(error: LoginError) {
        state.value = when (error) {
            LoginError.IncorrectPassword -> {
                state.value.copy(globalError = "Неверный пароль")
            }

            is LoginError.InvalidData -> {
                state.value.copy(
                    emailError = error.invalidFieldsHolder.email?.let(::getUiTextForInvalidField),
                    passwordError = error.invalidFieldsHolder.password?.let(::getUiTextForInvalidField),
                )
            }

            LoginError.Unknown -> {
                state.value.copy(globalError = "Неизвестная ошибка")
            }

            LoginError.UserNotFound -> {
               state.value.copy(globalError = "Пользователь не найден")
            }
        }
    }
}
