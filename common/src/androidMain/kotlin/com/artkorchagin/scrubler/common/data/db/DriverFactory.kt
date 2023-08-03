package com.artkorchagin.scrubler.common.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.artkorchagin.scrubler.Database
import org.koin.core.context.startKoin
import org.koin.fileProperties

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {

        startKoin {
            fileProperties()
        }

        return AndroidSqliteDriver(Database.Schema, context, "test.db")
    }
}