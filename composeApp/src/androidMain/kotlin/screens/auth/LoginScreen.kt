package screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.LoginComponent
import ui.component.CempButton
import ui.component.CempTextField

@Composable
fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.model.subscribeAsState()

    LoginContent(
        state = state,
        onIntent = { component.onIntent(it) },
        modifier = modifier
    )
}

@Composable
fun LoginContent(
    state: LoginComponent.Model,
    onIntent: (LoginComponent.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cemp Login")

        if (state.isLoading) {
            Spacer(Modifier.height(8.dp))
            CircularProgressIndicator()
            Spacer(Modifier.height(8.dp))
        } else {
            Spacer(Modifier.height(16.dp))
        }

        CempTextField(
            value = state.email,
            label = "Email",
            error = state.emailError,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onIntent(LoginComponent.Intent.EmailChanged(it)) }
        )
        Spacer(Modifier.height(8.dp))
        CempTextField(
            value = state.password,
            label = "Password",
            error = state.passwordError,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onIntent(LoginComponent.Intent.PasswordChanged(it)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        state.globalError?.let { errorText ->
            Spacer(Modifier.height(8.dp))
            Text(
                text = errorText,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(20.dp))

        CempButton(
            onClick = { onIntent(LoginComponent.Intent.LoginClicked) },
            text = "Sign in",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "Login Screen Preview")
@Composable
fun LoginContentPreview() {
    val previewState = LoginComponent.Model(
        email = "test@example.com",
        password = "password123",
        isLoading = false,
        emailError = null,
        passwordError = null,
        globalError = null
    )
    LoginContent(state = previewState, onIntent = {})
}

@Preview(showBackground = true, name = "Login Screen Loading Preview")
@Composable
fun LoginContentLoadingPreview() {
    val previewState = LoginComponent.Model(
        email = "test@example.com",
        password = "password123",
        isLoading = true,
        emailError = null,
        passwordError = null,
        globalError = null
    )
    LoginContent(state = previewState, onIntent = {})
}

@Preview(showBackground = true, name = "Login Screen With Errors Preview")
@Composable
fun LoginContentWithErrorsPreview() {
    val previewState = LoginComponent.Model(
        email = "test@example",
        password = "123",
        isLoading = false,
        emailError = "Invalid email format",
        passwordError = "Password too short",
        globalError = null,
    )
    LoginContent(state = previewState, onIntent = {})
}