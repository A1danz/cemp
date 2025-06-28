package component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.Theme

@Composable
fun CempProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier, color = Theme.colors.textColor)
}