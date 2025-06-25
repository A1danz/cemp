package screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import component.WelcomeComponent
import ui.component.CempButton

@Composable
fun WelcomeScreen(
    component: WelcomeComponent,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = "Welcome to CEMP",
            fontSize = TextUnit(24f, TextUnitType.Sp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        CempButton(
            onClick = { component.onIntent(WelcomeComponent.Intent.LoginClicked) },
            text = "Sign In"
        )

        Spacer(modifier = Modifier.height(8.dp))

        CempButton(
            onClick = { component.onIntent(WelcomeComponent.Intent.RegisterClicked) },
            text = "Sing Up"
        )
    }

}