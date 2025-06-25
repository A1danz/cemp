package com.cemp.design

data class AppColor(val hex: String)

data class FontSpec(val size: Double, val weight: Int)


object DarkTheme {
    val mainBackground = AppColor("#0D0F11")
    val secondaryBackground = AppColor("#141618")
    val blueText = AppColor("#6B9AB3")
    val textColor = AppColor("#FFFFFF")
}

object LightTheme {
    val mainBackground = AppColor("#FFFFFF")
    val secondaryBackground = AppColor("#F5F5F5")
    val blueText = AppColor("#1976D2")
    val textColor = AppColor("#000000")  // В светлой теме "белый" цвет для текста = черный
}

object Typography {
    // 36sp
    val text36ExtraBold = FontSpec(36.0, 900)
    
    // 28sp
    val text28Bold = FontSpec(28.0, 700)
    val text28SemiBold = FontSpec(28.0, 600)
    
    // 24sp
    val text24Bold = FontSpec(24.0, 700)
    val text24Medium = FontSpec(24.0, 500)
    
    // 20sp
    val text20SemiBold = FontSpec(20.0, 600)
    
    // 16sp
    val text16SemiBold = FontSpec(16.0, 600)
    
    // 14sp
    val text14Bold = FontSpec(14.0, 700)
    val text14SemiBold = FontSpec(14.0, 600)
    val text14Medium = FontSpec(14.0, 500)
    val text14MediumPlaceholder = FontSpec(14.0, 500)
    
    // 12sp
    val text12SemiBold = FontSpec(12.0, 600)
    val text12Medium = FontSpec(12.0, 500)
} 