package ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import theme.Theme

@Composable
fun CempText(
    text: String,
    isBlue: Boolean = false,
    textStyle: TextStyle = Theme.typography.text14Bold,
    modifier: Modifier = Modifier,
) {

    Text(
        text = text,
        color = if (isBlue) Theme.colors.blueTextColor else Theme.colors.textColor,
        style = textStyle,
        modifier = modifier,
    )
}