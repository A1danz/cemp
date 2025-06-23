package screens.auth

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cemp.feature.auth.presentation.component.LoginComponent
import com.cemp.feature.auth.presentation.component.WelcomeComponent

@Composable
fun LoginScreen(
    component: LoginComponent,
    modifier: Modifier = Modifier,
) {
    Text("Login")
}