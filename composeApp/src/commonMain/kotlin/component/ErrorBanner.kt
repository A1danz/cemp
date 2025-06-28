package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cemp.SharedRes.strings
import dev.icerock.moko.resources.compose.stringResource
import theme.Theme

@Composable
fun ErrorBanner(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().background(Theme.colors.mainBackgroundColor)
    ) {
        Text(
            text = stringResource(strings.feature_auth_unknown_error)
        )
    }
}