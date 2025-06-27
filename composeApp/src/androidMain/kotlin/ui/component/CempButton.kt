package ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.Theme

@Composable
fun CempButton(
    text: String,
    modifier: Modifier = Modifier,
    isWhite: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            (0.1).dp,
            if (isWhite) Theme.colors.mainBackgroundColor else Theme.colors.textColor
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isWhite) Theme.colors.textColor else Theme.colors.mainBackgroundColor,

            ),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            text,
            fontSize = 16.sp,
            color = if (isWhite) Theme.colors.mainBackgroundColor else Theme.colors.textColor
        )
    }
}

@Preview()
@Composable
fun CempButtonPreview() {
    AppTheme {
        CempButton("Test text") { }
    }
}

@Preview
@Composable
fun CempButtonWhitePreview() {
    AppTheme {
        CempButton("Test text", isWhite = false) { }
    }
}

@Preview
@Composable
fun CempButtonPreviewDark() {
    AppTheme(darkTheme = true) {
        CempButton("Test text") { }
    }
}

@Preview
@Composable
fun CempButtonWhitePreviewDark() {
    AppTheme(darkTheme = true) {
        CempButton("Test text", isWhite = false) { }
    }
}