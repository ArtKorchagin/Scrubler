package com.artkorchagin.scrubler.common

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(ScrublerSdk(DriverFactory())) }