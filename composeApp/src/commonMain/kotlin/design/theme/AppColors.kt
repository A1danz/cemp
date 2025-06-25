package design.theme

import androidx.compose.ui.graphics.Color

/**
 * Как использовать:
 * - В Compose: Text(color = AppColors.primary)
 * - На iOS: через framework как AppColors.shared.primary
 */
object AppColors : AppColorsInterface {
    val mainBackgroundColor = Color(0xFF0D0F11)
    val secondaryBackgroundColor = Color(0xFF141618)
    val blueTextColor = Color(0xFF6B9AB3)
    val whiteColor = Color(0xFFFFFFFF)
}

object AppColorsDark : AppColorsInterface {
    val mainBackgroundColor = Color(0xFF0D0F11)
    val secondaryBackgroundColor = Color(0xFF141618)
    val blueTextColor = Color(0xFF6B9AB3)
    val whiteColor = Color(0xFFFFFFFF)
} 