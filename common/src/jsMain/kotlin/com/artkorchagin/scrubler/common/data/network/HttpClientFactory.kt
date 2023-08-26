package com.artkorchagin.scrubler.common.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js

actual fun createPlatformHttpClient() = HttpClient(Js)