package com.artkorchagin.scrubler.common

import com.artkorchagin.scrubler.common.model.Player

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