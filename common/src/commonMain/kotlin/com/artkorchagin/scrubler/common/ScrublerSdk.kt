package com.artkorchagin.scrubler.common

import com.artkorchagin.scrubler.common.model.Player
import com.artkorchagin.scrubler.sqldelight.hockey.data.HockeyPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

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