// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.artkorchagin.scrubler.common.presentation

import androidx.compose.runtime.Composable
import com.artkorchagin.scrubler.common.data.db.DriverFactory
import com.artkorchagin.scrubler.common.data.db.ScrublerSdk
import com.artkorchagin.scrubler.common.presentation.App

@Composable
fun WebApp() {
    App(ScrublerSdk(DriverFactory()))
}