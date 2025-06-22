package com.cemp.data.di

import com.cemp.shared.data.database.CempDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        CempDatabase(get())
    }
}