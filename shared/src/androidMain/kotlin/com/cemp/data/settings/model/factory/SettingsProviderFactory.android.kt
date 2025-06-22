package com.cemp.data.settings.model.factory

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual open class SettingsProviderFactory : KoinComponent {

    private val context: Context by inject()

    actual fun provideSettings(): ObservableSettings {
        return SharedPreferencesSettings(
            delegate = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)
        )
    }

}