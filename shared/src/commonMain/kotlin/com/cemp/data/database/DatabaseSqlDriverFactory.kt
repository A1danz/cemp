package com.cemp.data.database

import app.cash.sqldelight.db.SqlDriver

expect open class DatabaseSqlDriverFactory() {

    fun createDriver(): SqlDriver

}

const val DATABASE_NAME: String = "cemp_db"
