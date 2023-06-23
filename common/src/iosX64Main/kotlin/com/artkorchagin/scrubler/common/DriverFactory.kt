// package com.artkorchagin.scrubler.common
//
// import app.cash.sqldelight.db.SqlDriver
// import app.cash.sqldelight.driver.native.NativeSqliteDriver
// import com.artkorchagin.scrubler.Database
//
// actual class DriverFactory {
//     actual fun createDriver(): SqlDriver {
//         return NativeSqliteDriver(Database.Schema, "test.db")
//     }
// }