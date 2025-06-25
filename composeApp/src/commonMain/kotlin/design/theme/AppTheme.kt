package design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Как использовать:
 * @Composable
 * fun MyScreen() {
 *     AppTheme {
 *         Text(
 *             text = "Привет!",
 *             color = Theme.colors.primary,
 *             style = Theme.typography.heading1
 *         )
 *     }
 * }
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        AppColorsDark
    } else {
        AppColors
    }
    
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides AppTypography,
        content = content
    )
}

/**
 * Локальные провайдеры
 * Это позволяет получать цвета и шрифты в любом месте приложения.
 */
private val LocalAppColors = staticCompositionLocalOf<AppColorsInterface> {
    error("AppColors not provided")
}

private val LocalAppTypography = staticCompositionLocalOf {
    AppTypography
}


interface AppColorsInterface {
    val mainBackgroundColor: androidx.compose.ui.graphics.Color
    val secondaryBackgroundColor: androidx.compose.ui.graphics.Color
    
    val blueTextColor: androidx.compose.ui.graphics.Color
    val whiteColor: androidx.compose.ui.graphics.Color
}

/**
 * - Theme.colors.primary
 * - Theme.typography.heading1
 */
object Theme {
    val colors: AppColorsInterface
        @Composable get() = LocalAppColors.current
    
    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
} 