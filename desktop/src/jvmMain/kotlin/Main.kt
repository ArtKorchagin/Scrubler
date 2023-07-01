// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.artkorchagin.scrubler.common.App
import com.artkorchagin.scrubler.common.DriverFactory
import com.artkorchagin.scrubler.common.ScrublerSdk


fun main() = application {
    Window(
        title = "Compose Ура!!!",
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 480.dp, height = 640.dp)
    ) {
        App(ScrublerSdk(DriverFactory()))
    }
}
