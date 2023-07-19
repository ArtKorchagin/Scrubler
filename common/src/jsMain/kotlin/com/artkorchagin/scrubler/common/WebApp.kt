// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.artkorchagin.scrubler.common

import androidx.compose.runtime.Composable

@Composable
fun WebApp() {
    App(ScrublerSdk(DriverFactory()))
}