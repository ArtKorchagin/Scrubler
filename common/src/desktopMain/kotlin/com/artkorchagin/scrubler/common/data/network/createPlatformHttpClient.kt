package com.artkorchagin.scrubler.common.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java

actual fun createPlatformHttpClient() = HttpClient(Java)