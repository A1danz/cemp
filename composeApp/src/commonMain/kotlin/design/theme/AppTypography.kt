package design.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * ✍️ Типографика приложения
 * 
 * Этот объект содержит все стили текста, которые используются в приложении.
 * Изменив стиль здесь, он изменится на всех платформах (iOS, Android, Desktop).
 * 
 * Как использовать:
 * - В Compose: Text(style = AppTypography.heading1)
 * - На iOS: нужно будет адаптировать размеры и веса шрифтов
 */
object AppTypography {
    
    // MARK: - Заголовки
    
    /** 
     * Большой заголовок (например, название экрана)
     * Пример: "Матчи", "Команды"
     */
    val heading1 = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Bold,    // Замени на нужный вес
        fontSize = 32.sp,               // Замени на нужный размер
        lineHeight = 40.sp              // Замени на нужную высоту строки
    )
    
    /** 
     * Средний заголовок (например, заголовки секций)
     * Пример: "Предстоящие матчи", "Топ команды"
     */
    val heading2 = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Bold,    // Замени на нужный вес
        fontSize = 24.sp,               // Замени на нужный размер
        lineHeight = 32.sp              // Замени на нужную высоту строки
    )
    
    /** 
     * Малый заголовок (например, заголовки карточек)
     * Пример: название команды в карточке
     */
    val heading3 = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.SemiBold, // Замени на нужный вес
        fontSize = 20.sp,               // Замени на нужный размер
        lineHeight = 28.sp              // Замени на нужную высоту строки
    )
    
    /** 
     * Подзаголовок (например, субтитры)
     * Пример: дата матча, счет
     */
    val subtitle = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Medium,  // Замени на нужный вес
        fontSize = 16.sp,               // Замени на нужный размер
        lineHeight = 24.sp              // Замени на нужную высоту строки
    )
    
    
    // MARK: - Основной текст
    
    /** 
     * Основной текст (например, описания)
     * Пример: описание матча, информация о команде
     */
    val body1 = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Normal,  // Замени на нужный вес
        fontSize = 16.sp,               // Замени на нужный размер
        lineHeight = 24.sp              // Замени на нужную высоту строки
    )
    
    /** 
     * Второстепенный текст (меньше основного)
     * Пример: дополнительная информация
     */
    val body2 = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Normal,  // Замени на нужный вес
        fontSize = 14.sp,               // Замени на нужный размер
        lineHeight = 20.sp              // Замени на нужную высоту строки
    )
    
    
    // MARK: - Кнопки
    
    /** 
     * Текст на кнопках
     * Пример: "Войти", "Зарегистрироваться"
     */
    val button = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Medium,  // Замени на нужный вес
        fontSize = 16.sp,               // Замени на нужный размер
        lineHeight = 24.sp              // Замени на нужную высоту строки
    )
    
    
    // MARK: - Подписи и мелкий текст
    
    /** 
     * Подписи (например, под изображениями)
     * Пример: "Обновлено 5 минут назад"
     */
    val caption = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Normal,  // Замени на нужный вес
        fontSize = 12.sp,               // Замени на нужный размер
        lineHeight = 16.sp              // Замени на нужную высоту строки
    )
    
    /** 
     * Очень мелкий текст (например, правовая информация)
     * Пример: "Условия использования"
     */
    val overline = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Normal,  // Замени на нужный вес
        fontSize = 10.sp,               // Замени на нужный размер
        lineHeight = 16.sp              // Замени на нужную высоту строки
    )
    
    
    // MARK: - Специальные стили
    
    /** 
     * Текст для ввода (в полях ввода)
     * Пример: текст в TextField
     */
    val input = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Normal,  // Замени на нужный вес
        fontSize = 16.sp,               // Замени на нужный размер
        lineHeight = 24.sp              // Замени на нужную высоту строки
    )
    
    /** 
     * Текст-плейсхолдер (подсказки в полях ввода)
     * Пример: "Введите email"
     */
    val placeholder = TextStyle(
        fontFamily = FontFamily.Default, // Замени на свой шрифт
        fontWeight = FontWeight.Normal,  // Замени на нужный вес
        fontSize = 16.sp,               // Замени на нужный размер
        lineHeight = 24.sp              // Замени на нужную высоту строки
    )
} 