package com.artkorchagin.scrubler.common.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClient() = HttpClient(Darwin)