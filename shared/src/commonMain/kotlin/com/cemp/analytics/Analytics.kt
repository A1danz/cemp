package com.cemp.analytics

/**
 * 📊 Интерфейс для аналитики
 * 
 * Этот интерфейс позволяет отправлять события аналитики на всех платформах.
 * На iOS и Android будет использоваться Firebase, на Desktop - заглушка.
 * 
 * Как использовать:
 * Analytics.logEvent("button_clicked", mapOf("button_name" to "login"))
 */
expect object Analytics {
    
    /**
     * Отправить событие аналитики
     * @param eventName название события (например, "screen_view", "button_click")
     * @param parameters дополнительные параметры события
     */
    fun logEvent(eventName: String, parameters: Map<String, Any>? = null)
    
    /**
     * Установить ID пользователя
     * @param userId уникальный ID пользователя
     */
    fun setUserId(userId: String?)
    
    /**
     * Установить свойство пользователя
     * @param name название свойства
     * @param value значение свойства
     */
    fun setUserProperty(name: String, value: String?)
    
    /**
     * Отправить информацию о просмотре экрана
     * @param screenName название экрана
     * @param screenClass класс экрана (опционально)
     */
    fun logScreenView(screenName: String, screenClass: String? = null)
} 