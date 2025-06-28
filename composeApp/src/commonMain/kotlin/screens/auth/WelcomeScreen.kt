package screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cemp.SharedRes.strings
import component.CempButton
import component.WelcomeComponent
import dev.icerock.moko.resources.compose.stringResource
import theme.Theme
import ui.component.CempText
import com.cemp.SharedRes.strings as stringsRes

@Composable
fun WelcomeScreen(
    component: WelcomeComponent,
    modifier: Modifier = Modifier,
) {
    WelcomeScreenContent(onIntent = { component.onIntent(it) }, modifier = modifier)
}

@Composable
fun WelcomeScreenContent(
    onIntent: (WelcomeComponent.Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)
            .padding(16.dp),
    ) {
        CempText(
            text = stringResource(strings.feature_auth_welcome_title),
            textStyle = Theme.typography.text28Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CempButton(
            onClick = { onIntent(WelcomeComponent.Intent.LoginClicked) },
            text = stringResource(strings.feature_auth_log_in_btn),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        CempButton(
            onClick = { onIntent(WelcomeComponent.Intent.RegisterClicked) },
            text = stringResource(stringsRes.feature_auth_register_btn),
            isWhite = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//@Preview
//@Composable
//fun WelcomeScreenPreview() {
//    AppTheme(darkTheme = true) {
//        WelcomeScreenContent({ })
//    }
//}