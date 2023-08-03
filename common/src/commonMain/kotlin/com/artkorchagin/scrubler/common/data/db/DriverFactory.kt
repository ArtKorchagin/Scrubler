package com.artkorchagin.scrubler.common.data.db

import app.cash.sqldelight.db.SqlDriver

// package com.artkorchagin.scrubler.common
//
// import app.cash.sqldelight.db.SqlDriver
//
// // in src/commonMain/kotlin
expect class DriverFactory {
    fun createDriver(): SqlDriver
}