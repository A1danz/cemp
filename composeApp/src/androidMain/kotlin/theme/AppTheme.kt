package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cemp.design.DarkTheme
import com.cemp.design.LightTheme
import com.cemp.design.Typography


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        AppColors(
            mainBackgroundColor = Color(parseHexColor(DarkTheme.mainBackground.hex)),
            secondaryBackgroundColor = Color(parseHexColor(DarkTheme.secondaryBackground.hex)),
            blueTextColor = Color(parseHexColor(DarkTheme.blueText.hex)),
            textColor = Color(parseHexColor(DarkTheme.textColor.hex)),
            secondaryTextColor = Color(parseHexColor(DarkTheme.secondaryTextColor.hex))

        )
    } else {
        AppColors(
            mainBackgroundColor = Color(parseHexColor(LightTheme.mainBackground.hex)),
            secondaryBackgroundColor = Color(parseHexColor(LightTheme.secondaryBackground.hex)),
            blueTextColor = Color(parseHexColor(LightTheme.blueText.hex)),
            textColor = Color(parseHexColor(LightTheme.textColor.hex)),
            secondaryTextColor = Color(parseHexColor(LightTheme.secondaryTextColor.hex))
        )
    }

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides AppTypography,
        content = content
    )
}

data class AppColors(
    val mainBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
    val blueTextColor: Color,
    val textColor: Color,
    val secondaryTextColor: Color,
)

object AppTypography {
    val text36ExtraBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text36ExtraBold.weight),
        fontSize = Typography.text36ExtraBold.size.sp,
        lineHeight = (Typography.text36ExtraBold.size * 1.2).sp
    )

    val text28Bold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text28Bold.weight),
        fontSize = Typography.text28Bold.size.sp,
        lineHeight = (Typography.text28Bold.size * 1.3).sp
    )

    val text28SemiBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text28SemiBold.weight),
        fontSize = Typography.text28SemiBold.size.sp,
        lineHeight = (Typography.text28SemiBold.size * 1.3).sp
    )

    val text24Bold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text24Bold.weight),
        fontSize = Typography.text24Bold.size.sp,
        lineHeight = (Typography.text24Bold.size * 1.3).sp
    )

    val text24Medium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text24Medium.weight),
        fontSize = Typography.text24Medium.size.sp,
        lineHeight = (Typography.text24Medium.size * 1.3).sp
    )

    val text20SemiBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text20SemiBold.weight),
        fontSize = Typography.text20SemiBold.size.sp,
        lineHeight = (Typography.text20SemiBold.size * 1.4).sp
    )

    val text16SemiBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text16SemiBold.weight),
        fontSize = Typography.text16SemiBold.size.sp,
        lineHeight = (Typography.text16SemiBold.size * 1.5).sp
    )

    val text14Bold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text14Bold.weight),
        fontSize = Typography.text14Bold.size.sp,
        lineHeight = (Typography.text14Bold.size * 1.4).sp
    )

    val text14SemiBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text14SemiBold.weight),
        fontSize = Typography.text14SemiBold.size.sp,
        lineHeight = (Typography.text14SemiBold.size * 1.4).sp
    )

    val text14Medium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text14Medium.weight),
        fontSize = Typography.text14Medium.size.sp,
        lineHeight = (Typography.text14Medium.size * 1.4).sp
    )

    val text14MediumPlaceholder = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text14MediumPlaceholder.weight),
        fontSize = Typography.text14MediumPlaceholder.size.sp,
        lineHeight = (Typography.text14MediumPlaceholder.size * 1.4).sp
    )

    val text12SemiBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text12SemiBold.weight),
        fontSize = Typography.text12SemiBold.size.sp,
        lineHeight = (Typography.text12SemiBold.size * 1.3).sp
    )

    val text12Medium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = getComposeWeight(Typography.text12Medium.weight),
        fontSize = Typography.text12Medium.size.sp,
        lineHeight = (Typography.text12Medium.size * 1.3).sp
    )

    private fun getComposeWeight(weight: Int): FontWeight = when (weight) {
        100 -> FontWeight.Thin
        200 -> FontWeight.ExtraLight
        300 -> FontWeight.Light
        400 -> FontWeight.Normal
        500 -> FontWeight.Medium
        600 -> FontWeight.SemiBold
        700 -> FontWeight.Bold
        800 -> FontWeight.ExtraBold
        900 -> FontWeight.Black
        else -> FontWeight.Normal
    }
}

private val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("AppColors not provided")
}

private val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("AppTypography not provided")
}

object Theme {
    val colors: AppColors
        @Composable get() = LocalAppColors.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current
}

private fun parseHexColor(hex: String): Long {
    val cleanHex = hex.removePrefix("#")
    return ("FF$cleanHex").toLong(16)
} 