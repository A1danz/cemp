package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.cemp.common.ext.logErr
import com.cemp.feature.auth.domain.model.error.RegistrationError
import com.cemp.feature.auth.domain.model.result.RegistrationResult
import com.cemp.feature.auth.domain.usecase.RegistrationUseCase
import component.RegisterComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DefaultRegisterComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit,
    private val onRegisterSuccess: () -> Unit
) : RegisterComponent, ComponentContext by componentContext, KoinComponent {

    private val state = MutableValue(RegisterComponent.Model.createInitialValue())
    override val model: Value<RegisterComponent.Model> = state

    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val registerUseCase by inject<RegistrationUseCase>()

    override fun onIntent(intent: RegisterComponent.Intent) {
        when (intent) {
            is RegisterComponent.Intent.ConfirmPasswordChanged -> onConfirmPasswordChanged(intent.value)
            is RegisterComponent.Intent.EmailChanged -> onEmailChanged(intent.value)
            is RegisterComponent.Intent.NameChanged -> onNameChanged(intent.value)
            is RegisterComponent.Intent.PasswordChanged -> onPasswordChanged(intent.value)
            RegisterComponent.Intent.RegisterClicked -> onRegisterClicked()
            is RegisterComponent.Intent.UsernameChanged -> onUsernameChanged(intent.value)
        }
    }

    private fun onEmailChanged(value: String) {
        state.value = state.value.copy(
            email = value,
            emailError = null,
        )
    }

    private fun onPasswordChanged(value: String) {
        state.value = state.value.copy(
            password = value,
            passwordError = getPasswordMatchingErrorOrNull(
                state.value.password,
                state.value.confirmPassword
            ),
        )
    }

    private fun onConfirmPasswordChanged(value: String) {
        state.value = state.value.copy(
            confirmPassword = value,
            confirmPasswordError = getPasswordMatchingErrorOrNull(
                state.value.password,
                state.value.confirmPassword
            ),
        )
    }

    private fun onNameChanged(value: String) {
        state.value = state.value.copy(
            name = value,
            nameError = null,
        )
    }

    private fun onUsernameChanged(value: String) {
        state.value = state.value.copy(
            username = value,
            usernameError = "",
        )
    }

    private fun onRegisterClicked() {
        scope.launch {
            state.value = state.value.copy(isLoading = true)
            kotlin.runCatching {
                registerUseCase.invoke(
                    name = state.value.name,
                    username = state.value.username,
                    email = state.value.email,
                    password = state.value.password,
                )
            }.onSuccess { result ->
                when (result) {
                    is RegistrationResult.Failed -> handleErrorResult(result.error)
                    RegistrationResult.Success -> onRegisterSuccess()
                }
            }.onFailure {
                logErr("Error during login", it)

                state.value =
                    state.value.copy(isLoading = false, globalError = "Неизвестная ошибка")
            }
        }
    }

    private fun getPasswordMatchingErrorOrNull(password: String, replayPassword: String): String? {
        return "Пароли не совпадают".takeIf { password != replayPassword }
    }

    private fun handleErrorResult(error: RegistrationError) {
        state.value = when (error) {
            is RegistrationError.InvalidData -> {
                error.registrationInvalidFieldsHolder.let { invalidFields ->
                    state.value.copy(
                        nameError = invalidFields.name?.let(::getUiTextForInvalidField),
                        usernameError = invalidFields.username?.let(::getUiTextForInvalidField),
                        emailError = invalidFields.email?.let(::getUiTextForInvalidField),
                        passwordError = invalidFields.password?.let(::getUiTextForInvalidField),
                        confirmPasswordError = invalidFields.password?.let(::getUiTextForInvalidField),
                    )
                }
            }

            RegistrationError.Unknown -> {
                state.value.copy(globalError = "Неизвестная ошибка")
            }

            RegistrationError.UserAlreadyExists -> {
                state.value.copy(globalError = "Пользователь уже существует")
            }
        }
    }
}
