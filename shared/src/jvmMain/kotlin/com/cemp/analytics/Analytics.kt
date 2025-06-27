package com.cemp.analytics

actual object Analytics {
    actual fun logEvent(eventName: String, parameters: Map<String, Any>?) {
        // JVM заглушка - можно добавить логирование в консоль
        println("Analytics.logEvent: $eventName with params: $parameters")
    }

    actual fun setUserId(userId: String?) {
        // JVM заглушка
        println("Analytics.setUserId: $userId")
    }

    actual fun setUserProperty(name: String, value: String?) {
        // JVM заглушка
        println("Analytics.setUserProperty: $name = $value")
    }

    actual fun logScreenView(screenName: String, screenClass: String?) {
        // JVM заглушка
        println("Analytics.logScreenView: $screenName (class: $screenClass)")
    }
} 