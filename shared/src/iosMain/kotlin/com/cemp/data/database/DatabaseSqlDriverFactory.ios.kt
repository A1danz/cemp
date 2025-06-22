package com.cemp.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.cemp.shared.data.database.CempDatabase

actual open class DatabaseSqlDriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CempDatabase.Schema, DATABASE_NAME)
    }
}