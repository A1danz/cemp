package com.cemp.data.settings.model.factory

import com.russhwolf.settings.ObservableSettings

expect open class SettingsProviderFactory() {

    fun provideSettings(): ObservableSettings
}

const val SETTINGS_NAME = "user_settings"