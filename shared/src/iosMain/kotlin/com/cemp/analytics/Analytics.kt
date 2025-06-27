package com.cemp.analytics

import dev.gitlive.firebase.analytics.FirebaseAnalytics
import dev.gitlive.firebase.analytics.analytics
import dev.gitlive.firebase.Firebase

/**
 * 🍎 iOS реализация Firebase Analytics
 * 
 * Использует GitLive Firebase Kotlin SDK, который под капотом использует Firebase iOS SDK.
 * Автоматически инициализируется при первом использовании.
 */
actual object Analytics {
    
    private val firebaseAnalytics: FirebaseAnalytics by lazy {
        Firebase.analytics
    }
    
    actual fun logEvent(eventName: String, parameters: Map<String, Any>?) {
        firebaseAnalytics.logEvent(eventName, parameters)
    }
    
    actual fun setUserId(userId: String?) {
        userId?.let { firebaseAnalytics.setUserId(it) }
    }
    
    actual fun setUserProperty(name: String, value: String?) {
        value?.let { firebaseAnalytics.setUserProperty(name, it) }
    }
    
    actual fun logScreenView(screenName: String, screenClass: String?) {
        val parameters = mutableMapOf<String, Any>(
            "screen_name" to screenName
        )
        screenClass?.let { parameters["screen_class"] = it }
        
        firebaseAnalytics.logEvent("screen_view", parameters)
    }
} 