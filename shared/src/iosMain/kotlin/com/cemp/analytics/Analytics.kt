package com.cemp.analytics

/**
 * 🍎 iOS реализация Analytics (временная заглушка)
 * Firebase временно отключен для тестирования
 */
actual object Analytics {
    
    actual fun logEvent(eventName: String, parameters: Map<String, Any>?) {
        println("📊 [iOS Analytics] Event: $eventName, Parameters: $parameters")
    }
    
    actual fun setUserId(userId: String?) {
        println("👤 [iOS Analytics] User ID: $userId")
    }
    
    actual fun setUserProperty(name: String, value: String?) {
        println("🏷️ [iOS Analytics] User Property: $name = $value")
    }
    
    actual fun logScreenView(screenName: String, screenClass: String?) {
        println("📱 [iOS Analytics] Screen: $screenName, Class: $screenClass")
    }
} 