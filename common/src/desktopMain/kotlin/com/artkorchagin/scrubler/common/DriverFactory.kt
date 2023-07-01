package com.artkorchagin.scrubler.common

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.artkorchagin.scrubler.Database
import java.io.File

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val databaseFile = File("test.db")
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databaseFile.absolutePath}")
        Database.Schema.create(driver)
        return driver
    }
}