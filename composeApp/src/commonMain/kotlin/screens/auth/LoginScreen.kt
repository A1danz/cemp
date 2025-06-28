package screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.LoginComponent
import dev.icerock.moko.resources.compose.stringResource
import theme.AppTheme
import theme.Theme
import component.CempButton
import component.CempProgressBar
import component.CempText
import component.CempTextField
import utils.StringResHelper
import com.cemp.SharedRes.strings as stringsRes

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
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            CempProgressBar()
            Spacer(Modifier.height(8.dp))
        }

        CempText(
            text = stringResource(stringsRes.feature_auth_login_title),
            textStyle = Theme.typography.text28Bold,
        )

        Spacer(Modifier.height(16.dp))

        CempTextField(
            value = state.email,
            label = stringResource(stringsRes.feature_auth_email_placeholder),
            error = state.emailError?.let { StringResHelper.toString(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onValueChange = { onIntent(LoginComponent.Intent.EmailChanged(it)) }
        )
        Spacer(Modifier.height(8.dp))
        CempTextField(
            value = state.password,
            label = stringResource(stringsRes.feature_auth_password_placeholder),
            error = state.passwordError?.let { StringResHelper.toString(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onValueChange = { onIntent(LoginComponent.Intent.PasswordChanged(it)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        state.globalError?.let { errorText ->
            Spacer(Modifier.height(8.dp))
            Text(
                text = errorText.let { StringResHelper.toString(it) },
                color = Color.Red
            )
        }

        Spacer(Modifier.height(8.dp))

        CempButton(
            onClick = { onIntent(LoginComponent.Intent.LoginClicked) },
            text = stringResource(stringsRes.feature_auth_log_in_btn),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        )
    }
}

//@Preview(showBackground = true, name = "Login Screen Preview")
//@Composable
//fun LoginContentPreview() {
//    val previewState = LoginComponent.Model(
//        email = "test@example.com",
//        password = "password123",
//        isLoading = false,
//        emailError = null,
//        passwordError = null,
//        globalError = null
//    )
//
//    AppTheme {
//        LoginContent(state = previewState, onIntent = {})
//    }
//
//}

//@Preview(showBackground = true, name = "Login Screen Loading Preview")
//@Composable
//fun LoginContentLoadingPreview() {
//    val previewState = LoginComponent.Model(
//        email = "test@example.com",
//        password = "password123",
//        isLoading = true,
//        emailError = null,
//        passwordError = null,
//        globalError = null
//    )
//
//    AppTheme {
//        LoginContent(state = previewState, onIntent = {})
//
//    }
//
//}
//
//@Preview(showBackground = true, name = "Login Screen With Errors Preview")
//@Composable
//fun LoginContentWithErrorsPreview() {
//    val previewState = LoginComponent.Model(
//        email = "test@example",
//        password = "123",
//        isLoading = false,
//        emailError = null,
//        passwordError = null,
//        globalError = null,
//    )
//
//    AppTheme {
//        LoginContent(state = previewState, onIntent = {})
//    }
//}