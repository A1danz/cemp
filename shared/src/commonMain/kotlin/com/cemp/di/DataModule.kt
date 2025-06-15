package com.cemp.di

import com.cemp.data.di.networkModule
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    includes(networkModule)
}