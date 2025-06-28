package com.cemp.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * 🤖 Android реализация Firebase Analytics
 * 
 * Использует официальный Firebase Android SDK.
 * Автоматически инициализируется при первом использовании.
 */
actual object Analytics : KoinComponent {
    
    private val context: Context by inject()
    
    private val firebaseAnalytics: FirebaseAnalytics by lazy {
        // Инициализируем Firebase если еще не инициализирован
        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context)
        }
        Firebase.analytics.apply {
            setAnalyticsCollectionEnabled(true)
        }
    }
    
    actual fun logEvent(eventName: String, parameters: Map<String, Any>?) {
        val bundle = Bundle()
        parameters?.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Long -> bundle.putLong(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                else -> bundle.putString(key, value.toString())
            }
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }
    
    actual fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }
    
    actual fun setUserProperty(name: String, value: String?) {
        firebaseAnalytics.setUserProperty(name, value)
    }
    
    actual fun logScreenView(screenName: String, screenClass: String?) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            screenClass?.let { putString(FirebaseAnalytics.Param.SCREEN_CLASS, it) }
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
} 