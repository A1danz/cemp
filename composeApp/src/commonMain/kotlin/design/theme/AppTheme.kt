package design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * 🎨 Главная тема приложения
 * 
 * Эта функция оборачивает все приложение и предоставляет доступ к цветам и шрифтам.
 * 
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
    darkTheme: Boolean = isSystemInDarkTheme(), // Автоматически определяет темную тему
    content: @Composable () -> Unit
) {
    // Выбираем цвета в зависимости от темы
    val colors = if (darkTheme) {
        AppColorsDark // Темные цвета
    } else {
        AppColors     // Светлые цвета
    }
    
    // Предоставляем цвета и типографику всему приложению
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides AppTypography,
        content = content
    )
}

/**
 * 🔧 Локальные провайдеры (техническая часть)
 * 
 * Это позволяет получать цвета и шрифты в любом месте приложения.
 * Не меняй эту часть, если не знаешь что делаешь.
 */
private val LocalAppColors = staticCompositionLocalOf<AppColorsInterface> {
    error("AppColors not provided")
}

private val LocalAppTypography = staticCompositionLocalOf {
    AppTypography
}

/**
 * 🎯 Интерфейс для цветов
 * 
 * Это нужно для того, чтобы светлые и темные цвета имели одинаковую структуру.
 */
interface AppColorsInterface {
    // Основные цвета
    val primary: androidx.compose.ui.graphics.Color
    val onPrimary: androidx.compose.ui.graphics.Color
    val secondary: androidx.compose.ui.graphics.Color
    val onSecondary: androidx.compose.ui.graphics.Color
    
    // Фоны
    val background: androidx.compose.ui.graphics.Color
    val onBackground: androidx.compose.ui.graphics.Color
    val surface: androidx.compose.ui.graphics.Color
    val onSurface: androidx.compose.ui.graphics.Color
    
    // Состояния
    val error: androidx.compose.ui.graphics.Color
    val onError: androidx.compose.ui.graphics.Color
    val success: androidx.compose.ui.graphics.Color
    val warning: androidx.compose.ui.graphics.Color
    
    // Дополнительные
    val outline: androidx.compose.ui.graphics.Color
    val disabled: androidx.compose.ui.graphics.Color
}

/**
 * 🌟 Глобальный объект Theme
 * 
 * Используй его для получения цветов и шрифтов в любом месте приложения:
 * - Theme.colors.primary
 * - Theme.typography.heading1
 */
object Theme {
    val colors: AppColorsInterface
        @Composable get() = LocalAppColors.current
    
    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
} 