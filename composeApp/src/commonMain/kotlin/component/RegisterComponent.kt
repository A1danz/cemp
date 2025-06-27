package component

import com.arkivanov.decompose.value.Value
import component.LoginComponent.Model
import dev.icerock.moko.resources.desc.StringDesc

interface RegisterComponent : BaseAuthComponent {

    val model: Value<Model>

    fun onIntent(registerIntent: Intent)

    data class Model(
        val isLoading: Boolean,
        val name: String,
        val nameError: StringDesc?,
        val username: String,
        val usernameError: StringDesc?,
        val email: String,
        val emailError: StringDesc?,
        val password: String,
        val passwordError: StringDesc?,
        val confirmPassword: String,
        val confirmPasswordError: StringDesc?,
        val globalError: StringDesc?,
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