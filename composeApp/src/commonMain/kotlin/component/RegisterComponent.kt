package component

import com.arkivanov.decompose.value.Value
import component.LoginComponent.Model

interface RegisterComponent : BaseAuthComponent {

    val model: Value<Model>

    fun onIntent(intent: Intent)

    data class Model(
        val isLoading: Boolean,
        val name: String,
        val nameError: String?,
        val username: String,
        val usernameError: String?,
        val email: String,
        val emailError: String?,
        val password: String,
        val passwordError: String?,
        val confirmPassword: String,
        val confirmPasswordError: String?,
        val globalError: String?,
    ) {
        companion object {
            fun createInitialValue() = Model(
                isLoading = false,
                name = "",
                nameError = null,
                username = "",
                usernameError = null,
                email = "",
                emailError = null,
                password = "",
                passwordError = null,
                confirmPassword = "",
                confirmPasswordError = null,
                globalError = null,
            )
        }
    }

    sealed interface Intent {
        data class NameChanged(val value: String) : Intent
        data class UsernameChanged(val value: String) : Intent
        data class EmailChanged(val value: String) : Intent
        data class PasswordChanged(val value: String) : Intent
        data class ConfirmPasswordChanged(val value: String) : Intent
        data object RegisterClicked : Intent
    }
}