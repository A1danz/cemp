package screens.auth

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cemp.feature.auth.presentation.component.LoginComponent
import com.cemp.feature.auth.presentation.component.RegisterComponent
import com.cemp.feature.auth.presentation.component.WelcomeComponent

@Composable
fun RegisterScreen(
    component: RegisterComponent,
    modifier: Modifier = Modifier,
) {
    Text("Register")


}