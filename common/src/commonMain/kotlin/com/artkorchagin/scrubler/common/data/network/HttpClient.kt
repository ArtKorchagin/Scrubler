package com.artkorchagin.scrubler.common.data.network

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient() = createPlatformHttpClient().config {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.BODY
    }
}