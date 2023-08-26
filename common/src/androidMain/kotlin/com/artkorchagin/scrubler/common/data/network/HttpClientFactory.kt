package com.artkorchagin.scrubler.common.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

actual fun createPlatformHttpClient() = HttpClient(Android)