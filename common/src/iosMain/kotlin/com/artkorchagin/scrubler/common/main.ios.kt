package com.artkorchagin.scrubler.common

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.artkorchagin.scrubler.common.components.DefaultRootComponent
import com.artkorchagin.scrubler.common.components.RootContent
import platform.UIKit.UIViewController

//fun MainViewController() = ComposeUIViewController { App(ScrublerSdk(DriverFactory())) }

fun MainViewController(): UIViewController {
    // initKoin()
    return ComposeUIViewController {
        val lifecycle = LifecycleRegistry()
        val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

        RootContent(DefaultRootComponent(rootComponentContext))
        // CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        //    App()
        // }
    }
}
