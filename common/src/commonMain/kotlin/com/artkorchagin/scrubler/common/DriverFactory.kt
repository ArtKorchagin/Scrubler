package com.artkorchagin.scrubler.common

import app.cash.sqldelight.db.SqlDriver
import com.artkorchagin.scrubler.Database
import com.artkorchagin.scrubler.sqldelight.hockey.data.HockeyPlayer
import com.artkorchagin.scrubler.sqldelight.hockey.data.PlayerQueries


// package com.artkorchagin.scrubler.common
//
// import app.cash.sqldelight.db.SqlDriver
//
// // in src/commonMain/kotlin
expect class DriverFactory {
    fun createDriver(): SqlDriver
}

// //
// fun createDatabase(driverFactory: DriverFactory): Database {
//     val driver = driverFactory.createDriver()
//     val database = Database(driver)
//
//
//     // TODO: Examples!
//     val playerQueries: PlayerQueries = database.playerQueries
//
//     println(playerQueries.selectAll().executeAsList())
//     // Prints [HockeyPlayer(15, "Ryan Getzlaf")]
//
//     playerQueries.insert(player_number = 10, full_name = "Corey Perry")
//     println(playerQueries.selectAll().executeAsList())
//     // Prints [HockeyPlayer(15, "Ryan Getzlaf"), HockeyPlayer(10, "Corey Perry")]
//
//     val player = HockeyPlayer(10, "Ronald McDonald")
//     playerQueries.insertFullPlayerObject(player)
//     // TODO: Examples!
//
//     ///     val playerQueries: PlayerQueries = database.playerQueries
//
//     // Do more work with the database (see below).
//
//     return database
// }