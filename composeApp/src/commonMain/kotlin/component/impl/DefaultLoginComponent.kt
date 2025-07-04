package component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.cemp.common.ext.logErr
import com.cemp.feature.auth.domain.model.error.LoginError
import com.cemp.feature.auth.domain.model.result.LoginResult
import com.cemp.feature.auth.domain.usecase.LoginUseCase
import component.LoginComponent
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.cemp.SharedRes.strings as stringsRes

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
        state.value = state.value.copy(email = value, emailError = null)
    }

    private fun onPasswordChanged(value: String) {
        state.value = state.value.copy(password = value, passwordError = null)
    }

    private fun onLoginClicked() {
        scope.launch {
            state.value = state.value.copy(isLoading = true)

            runCatching {
                loginUseCase(state.value.email, state.value.password)
            }.onSuccess { result ->
                when (result) {
                    is LoginResult.Failed -> handleFailedResult(result.error)
                    LoginResult.Success -> onLoginSuccess()
                }
            }.onFailure {
                logErr("Error during login", it)

                state.value = state.value.copy(
                    isLoading = false,
                    globalError = stringsRes.feature_auth_unknown_error.desc()
                )
            }
        }
    }

    private fun handleFailedResult(error: LoginError) {
        state.value = when (error) {
            LoginError.IncorrectPassword -> {
                state.value.copy(globalError = stringsRes.feature_auth_incorrect_pwd.desc())
            }

            is LoginError.InvalidData -> {
                state.value.copy(
                    emailError = error.invalidFieldsHolder.email?.let(::getUiTextForInvalidField),
                    passwordError = error.invalidFieldsHolder.password?.let(::getUiTextForInvalidField),
                )
            }

            LoginError.Unknown -> {
                state.value.copy(globalError = stringsRes.feature_auth_unknown_error.desc())
            }

            LoginError.UserNotFound -> {
                state.value.copy(globalError = stringsRes.feature_auth_user_not_found.desc())
            }
        }.copy(isLoading = false)
    }
}
