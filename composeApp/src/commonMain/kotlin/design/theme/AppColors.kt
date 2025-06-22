package design.theme

import androidx.compose.ui.graphics.Color

/**
 * 🎨 Цвета приложения
 * 
 * Этот объект содержит все цвета, которые используются в приложении.
 * Изменив цвет здесь, он изменится на всех платформах (iOS, Android, Desktop).
 * 
 * Как использовать:
 * - В Compose: Text(color = AppColors.primary)
 * - На iOS: через framework как AppColors.shared.primary
 */
object AppColors : AppColorsInterface {
    
    // MARK: - Основные цвета бренда
    
    /** Основной цвет приложения (например, синий для кнопок) */
    val primary = Color(0xFF000000) // Замени на свой цвет
    
    /** Цвет текста на основном цвете */
    val onPrimary = Color(0xFFFFFFFF) // Замени на свой цвет
    
    /** Вторичный цвет (например, для второстепенных кнопок) */
    val secondary = Color(0xFF000000) // Замени на свой цвет
    
    /** Цвет текста на вторичном цвете */
    val onSecondary = Color(0xFFFFFFFF) // Замени на свой цвет
    
    
    // MARK: - Фоны и поверхности
    
    /** Основной фон приложения */
    val background = Color(0xFFFFFFFF) // Замени на свой цвет
    
    /** Цвет текста на фоне */
    val onBackground = Color(0xFF000000) // Замени на свой цвет
    
    /** Цвет поверхностей (карточки, модальные окна) */
    val surface = Color(0xFFFFFFFF) // Замени на свой цвет
    
    /** Цвет текста на поверхностях */
    val onSurface = Color(0xFF000000) // Замени на свой цвет
    
    
    // MARK: - Состояния
    
    /** Цвет ошибок */
    val error = Color(0xFFFF0000) // Замени на свой цвет
    
    /** Цвет текста на ошибках */
    val onError = Color(0xFFFFFFFF) // Замени на свой цвет
    
    /** Цвет успеха */
    val success = Color(0xFF00FF00) // Замени на свой цвет
    
    /** Цвет предупреждений */
    val warning = Color(0xFFFFFF00) // Замени на свой цвет
    
    
    // MARK: - Дополнительные цвета
    
    /** Цвет границ */
    val outline = Color(0xFF000000) // Замени на свой цвет
    
    /** Неактивные элементы */
    val disabled = Color(0xFF888888) // Замени на свой цвет
}

/**
 * 🌙 Темные цвета (для Dark Mode)
 * 
 * Когда пользователь включает темную тему, используются эти цвета.
 */
object AppColorsDark : AppColorsInterface {
    
    // MARK: - Основные цвета бренда (обычно такие же)
    
    val primary = Color(0xFF000000) // Замени на свой цвет
    val onPrimary = Color(0xFFFFFFFF) // Замени на свой цвет
    val secondary = Color(0xFF000000) // Замени на свой цвет
    val onSecondary = Color(0xFFFFFFFF) // Замени на свой цвет
    
    
    // MARK: - Фоны и поверхности (обычно темные)
    
    val background = Color(0xFF000000) // Замени на темный цвет
    val onBackground = Color(0xFFFFFFFF) // Замени на светлый текст
    val surface = Color(0xFF111111) // Замени на темную поверхность
    val onSurface = Color(0xFFFFFFFF) // Замени на светлый текст
    
    
    // MARK: - Состояния
    
    val error = Color(0xFFFF6B6B) // Замени на свой цвет
    val onError = Color(0xFF000000) // Замени на свой цвет
    val success = Color(0xFF51CF66) // Замени на свой цвет
    val warning = Color(0xFFFFD43B) // Замени на свой цвет
    
    
    // MARK: - Дополнительные цвета
    
    val outline = Color(0xFF666666) // Замени на свой цвет
    val disabled = Color(0xFF444444) // Замени на свой цвет
} 