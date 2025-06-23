package component

import com.arkivanov.decompose.value.Value

interface LoginComponent {

    val model: Value<Model>

    fun onEmailChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onLoginClick()

    data class Model(
        val email: String,
        val password: String,
    )
}