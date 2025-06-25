package sample.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cemp.getFibonacciNumbers
import design.theme.AppTheme
import design.theme.Theme

@Composable
fun App() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.mainBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Главный заголовок с новым стилем
                Text(
                    text = "🎮 CEMP",
                    style = Theme.typography.text28Bold,
                    color = Theme.colors.whiteColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Подзаголовок
                Text(
                    text = "CS:GO Esports Platform",
                    style = Theme.typography.text16SemiBold,
                    color = Theme.colors.blueTextColor,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                // Демонстрация функции
                Text(
                    text = "getFibonacciNumbers(7)=${getFibonacciNumbers(7).joinToString(", ")}",
                    style = Theme.typography.text12Medium,
                    color = Theme.colors.blueTextColor
                )
            }
        }
    }
}