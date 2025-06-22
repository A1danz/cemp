package com.cemp.data.settings.model.factory

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings

actual open class SettingsProviderFactory actual constructor() {
    actual fun provideSettings(): ObservableSettings {
        return NSUserDefaultsSettings.Factory().create(SETTINGS_NAME)
    }
}