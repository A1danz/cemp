package com.cemp.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.cemp.shared.data.database.CempDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual open class DatabaseSqlDriverFactory actual constructor() : KoinComponent {

    private val context: Context by inject()

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CempDatabase.Schema, context, DATABASE_NAME)
    }
}