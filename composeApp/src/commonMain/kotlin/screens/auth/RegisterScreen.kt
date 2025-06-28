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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ui.component.CempButton
import component.RegisterComponent
import dev.icerock.moko.resources.compose.stringResource
import theme.Theme
import ui.component.CempProgressBar
import ui.component.CempText
import ui.component.CempTextField
import utils.StringResHelper
import com.cemp.SharedRes.strings as stringsRes

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
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (state.isLoading) {
            CempProgressBar()
            Spacer(Modifier.height(8.dp))
        }

        CempText(
            stringResource(stringsRes.feature_auth_registration_title),
            textStyle = Theme.typography.text28Bold
        )

        Spacer(Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
        ) {
            CempTextField(
                value = state.name,
                label = stringResource(stringsRes.feature_auth_name_placeholder),
                error = state.nameError?.let { StringResHelper.toString(it) },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onIntent(RegisterComponent.Intent.NameChanged(it)) }
            )

            CempTextField(
                value = state.email,
                label = stringResource(stringsRes.feature_auth_email_placeholder),
                error = state.emailError?.let { StringResHelper.toString(it) },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onIntent(RegisterComponent.Intent.EmailChanged(it)) }
            )

            CempTextField(
                value = state.username,
                label = stringResource(stringsRes.feature_auth_username_placeholder),
                error = state.usernameError?.let { StringResHelper.toString(it) },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onIntent(RegisterComponent.Intent.UsernameChanged(it)) },
            )

            CempTextField(
                value = state.password,
                label = stringResource(stringsRes.feature_auth_password_placeholder),
                error = state.passwordError?.let { StringResHelper.toString(it) },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onIntent(RegisterComponent.Intent.PasswordChanged(it)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            CempTextField(
                value = state.confirmPassword,
                label = stringResource(stringsRes.feature_auth_confirm_password_placeholder),
                error = state.confirmPasswordError?.let { StringResHelper.toString(it) },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onIntent(RegisterComponent.Intent.ConfirmPasswordChanged(it)) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        Spacer(Modifier.height(8.dp))

        CempButton(
            stringResource(stringsRes.feature_auth_register_btn),
            Modifier
                .fillMaxWidth()
                .background(Theme.colors.mainBackgroundColor)
                .padding(horizontal = 48.dp)
        ) { onIntent(RegisterComponent.Intent.RegisterClicked) }
    }


}

//@Preview(showBackground = true, name = "Register Screen Preview")
//@Composable
//fun RegisterContentPreview() {
//    val previewState = RegisterComponent.Model(
//        name = "",
//        email = "john.doe@example.com",
//        username = "johndoe",
//        password = "password123",
//        confirmPassword = "password123",
//        isLoading = false,
//        nameError = null,
//        emailError = null,
//        usernameError = null,
//        passwordError = null,
//        confirmPasswordError = null,
//        globalError = null,
//    )
//
//    AppTheme(true) {
//        RegisterContent(previewState) { }
//    }
//}

