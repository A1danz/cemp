package com.cemp.di

import com.cemp.data.crypt.CryptoManager
import com.cemp.data.crypt.impl.CryptoManagerImpl
import com.cemp.data.di.databaseModule
import com.cemp.data.di.networkModule
import com.cemp.data.di.settingsModule
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single<CryptoManager> { CryptoManagerImpl() }

    includes(
        networkModule,
        databaseModule,
        settingsModule,
        analyticsModule,
    )
}