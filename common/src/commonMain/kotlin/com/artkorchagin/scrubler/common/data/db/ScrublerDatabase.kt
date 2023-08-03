package com.artkorchagin.scrubler.common.data.db

import com.artkorchagin.scrubler.Database
import com.artkorchagin.scrubler.common.data.model.Player
import com.artkorchagin.scrubler.sqldelight.hockey.data.HockeyPlayer
import com.artkorchagin.scrubler.sqldelight.hockey.data.PlayerQueries

internal class ScrublerDatabase(
    driverFactory: DriverFactory
) {
    private val driver = driverFactory.createDriver()
    private val database = Database(driver)

    //TODO: Word queries or else
    private val playerQueries: PlayerQueries = database.playerQueries

    internal fun getAllPlayers(): List<Player> {
        return playerQueries.selectAll(::mapPlayer).executeAsList()
    }

    internal fun getMaxPlayer(): Long {
        return playerQueries.maxPlayer().executeAsOneOrNull()?.MAX ?: 0
    }

    internal fun createPlayer(number: Long, name: String) {
        println("Add Player: [$number] $name")

        playerQueries.transaction {
            playerQueries.insertFullPlayerObject(HockeyPlayer(number, name))
        }
    }

    internal fun clearDatabase() {
        playerQueries.transaction {
            playerQueries.removeAllPlayers()
        }
    }

    private fun mapPlayer(index: Long, name: String) = Player(index, name)
}