package screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.cemp.feature.auth.presentation.component.WelcomeComponent

@Composable
fun WelcomeScreen(
    component: WelcomeComponent,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to CEMP", fontSize = TextUnit(24f, TextUnitType.Sp))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = component::onLoginClick) {
            Text("Go to login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = component::onRegisterClick) {
            Text("Go to register")
        }
    }

}