package com.cemp.data.di

import app.cash.sqldelight.db.SqlDriver
import com.cemp.data.database.DatabaseSqlDriverFactory
import com.cemp.shared.data.database.CempDatabase
import com.cemp.shared.data.database.user.UserQueries
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseSqlDriverFactory().createDriver() }

    single {
        CempDatabase(get())
    }

    single<UserQueries> { get<CempDatabase>().userQueries }
}