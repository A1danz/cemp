package component

import com.arkivanov.decompose.value.Value
import com.cemp.feature.auth.domain.model.error.InvalidField

interface LoginComponent : BaseAuthComponent {

    val model: Value<Model>

    fun onIntent(intent: Intent)

    data class Model(
        val isLoading: Boolean,
        val email: String,
        val emailError: String?,
        val password: String,
        val passwordError: String?,
        val globalError: String?,
    ) {
        companion object {
            fun createInitialValue() = Model(
                isLoading = false,
                email = "",
                emailError = null,
                password = "",
                passwordError = null,
                globalError = null,
            )
        }
    }

    sealed interface Intent {
        data class EmailChanged(val value: String) : Intent
        data class PasswordChanged(val value: String) : Intent
        data object LoginClicked : Intent
    }
}