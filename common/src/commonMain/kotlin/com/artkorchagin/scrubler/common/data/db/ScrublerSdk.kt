package com.artkorchagin.scrubler.common.data.db

import com.artkorchagin.scrubler.common.data.model.Player

class ScrublerSdk(
    driverFactory: DriverFactory
) {

    private val scrublerDatabase = ScrublerDatabase(driverFactory)

    fun getPlayers(): List<Player> {
        return scrublerDatabase.getAllPlayers()
    }

    fun addPlayer(name: String) {
        scrublerDatabase.createPlayer(scrublerDatabase.getMaxPlayer() + 1, name)
    }
}