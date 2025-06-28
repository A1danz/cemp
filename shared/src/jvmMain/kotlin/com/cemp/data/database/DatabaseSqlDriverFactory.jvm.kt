package com.cemp.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.cemp.shared.data.database.CempDatabase

actual open class DatabaseSqlDriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:$DATABASE_NAME")
        
        // Создаем схему базы данных при первом запуске
        try {
            CempDatabase.Schema.create(driver)
        } catch (e: Exception) {
            // Если схема уже существует, игнорируем ошибку
            println("Database schema already exists or error creating: ${e.message}")
        }
        
        return driver
    }
}