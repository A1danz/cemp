package component

import com.arkivanov.decompose.value.Value
import dev.icerock.moko.resources.desc.StringDesc

interface LoginComponent : BaseAuthComponent {

    val model: Value<Model>

    fun onIntent(loginIntent: Intent)

    data class Model(
        val isLoading: Boolean,
        val email: String,
        val emailError: StringDesc?,
        val password: String,
        val passwordError: StringDesc?,
        val globalError: StringDesc?,
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