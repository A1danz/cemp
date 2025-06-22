package com.cemp.data.settings.model.factory

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings

actual open class SettingsProviderFactory actual constructor() {
    actual fun provideSettings(): ObservableSettings {
        return PreferencesSettings.Factory().create(SETTINGS_NAME)
    }

}