package com.cemp.di

import com.cemp.data.crypt.CryptoManager
import com.cemp.data.crypt.impl.CryptoManagerImpl
import com.cemp.data.di.databaseModule
import com.cemp.data.di.networkModule
import com.cemp.data.di.settingsModule
import com.cemp.data.repository.MatchesRepositoryImpl
import com.cemp.data.repository.TeamsRepositoryImpl
import com.cemp.domain.repository.MatchesRepository
import com.cemp.domain.repository.TeamsRepository
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
    single<MatchesRepository> { MatchesRepositoryImpl(get(), get()) }
    single<TeamsRepository> { TeamsRepositoryImpl(get(), get()) }

    includes(
        networkModule,
        databaseModule,
        settingsModule,
    )
}