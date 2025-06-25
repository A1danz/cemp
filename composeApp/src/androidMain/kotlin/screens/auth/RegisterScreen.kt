package screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.RegisterComponent
import ui.component.CempButton
import ui.component.CempTextField

@Composable
fun RegisterScreen(
    component: RegisterComponent,
) {
    val state by component.model.subscribeAsState()

    RegisterContent(state) { component.onIntent(it) }
}

@Composable
fun RegisterContent(
    state: RegisterComponent.Model,
    onIntent: (RegisterComponent.Intent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Register")
        Spacer(Modifier.height(8.dp))
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        Spacer(Modifier.height(8.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CempTextField(
                value = state.name,
                label = "Name",
                error = state.name,
                onValueChange = { onIntent(RegisterComponent.Intent.NameChanged(it)) }
            )

            CempTextField(
                value = state.email,
                label = "E-mail",
                error = state.emailError,
                onValueChange = { onIntent(RegisterComponent.Intent.EmailChanged(it)) }
            )

            CempTextField(
                value = state.username,
                label = "Username",
                onValueChange = { onIntent(RegisterComponent.Intent.UsernameChanged(it)) },
                error = state.usernameError
            )

            CempTextField(
                value = state.password,
                label = "Password",
                error = state.passwordError,
                onValueChange = { onIntent(RegisterComponent.Intent.PasswordChanged(it)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            CempTextField(
                value = state.confirmPassword,
                label = "Confirm password",
                error = state.confirmPasswordError,
                onValueChange = { onIntent(RegisterComponent.Intent.ConfirmPasswordChanged(it)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        Spacer(Modifier.height(20.dp))

        CempButton("Register") { onIntent(RegisterComponent.Intent.RegisterClicked) }
    }
}

@Preview(showBackground = true, name = "Register Screen Preview")
@Composable
fun RegisterContentPreview() {
    val previewState = RegisterComponent.Model(
        name = "John Doe",
        email = "john.doe@example.com",
        username = "johndoe",
        password = "password123",
        confirmPassword = "password123",
        isLoading = false,
        nameError = null,
        emailError = null,
        usernameError = null,
        passwordError = null,
        confirmPasswordError = "",
        globalError = "",
    )
    RegisterContent(state = previewState, onIntent = {})
}

