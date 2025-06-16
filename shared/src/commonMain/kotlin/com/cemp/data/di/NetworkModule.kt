package com.cemp.data.di

import com.cemp.data.network.HttpEngineFactory
import com.cemp.data.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpEngineFactory()
    }

    single {
        createHttpClient(
            engine = get<HttpEngineFactory>().createEngine(),
            json = get(),
        )
    }
}