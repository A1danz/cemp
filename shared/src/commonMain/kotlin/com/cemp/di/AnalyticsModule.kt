package com.cemp.di

import com.cemp.analytics.Analytics
import org.koin.dsl.module

/**
 * 📊 Модуль аналитики для Dependency Injection
 * 
 * Предоставляет доступ к Analytics через DI.
 * Можно использовать как:
 * - Прямой вызов: Analytics.logEvent(...)
 * - Через DI: val analytics by inject<Analytics>()
 */
val analyticsModule = module {
    single { Analytics }
} 