package component

import com.arkivanov.decompose.value.Value

interface RegisterComponent {

    val model: Value<Model>

    fun onNameChanged(value: String)
    fun onUsernameChanged(value: String)
    fun onEmailChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onConfirmPasswordChanged(value: String)
    fun onRegisterClick()

    data class Model(
        val name: String,
        val username: String,
        val email: String,
        val password: String,
        val confirmPassword: String,
    )
}