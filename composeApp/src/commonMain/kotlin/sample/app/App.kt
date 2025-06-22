package sample.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cemp.getFibonacciNumbers
import design.theme.AppTheme
import design.theme.Theme

@Composable
fun App() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "getFibonacciNumbers(7)=${getFibonacciNumbers(7).joinToString(", ")}",
                style = Theme.typography.body1,
                color = Theme.colors.onBackground
            )
        }
    }
}