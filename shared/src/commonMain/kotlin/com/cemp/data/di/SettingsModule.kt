package com.cemp.data.di

import com.cemp.data.repository.UserLocalRepositoryImpl
import com.cemp.data.serializer.UserDataSerializer
import com.cemp.data.settings.model.factory.SettingsProviderFactory
import com.cemp.domain.repository.UserLocalRepository
import com.russhwolf.settings.ObservableSettings
import org.koin.dsl.module

val settingsModule = module {
    single { UserDataSerializer(get()) }
    single { SettingsProviderFactory() }
    single<ObservableSettings> {
        get<SettingsProviderFactory>().provideSettings()
    }

    single<UserLocalRepository> {
        UserLocalRepositoryImpl(get(), get(), get())
    }
}