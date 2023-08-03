package com.artkorchagin.scrubler.common

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.artkorchagin.scrubler.common.di.initKoin
import com.artkorchagin.scrubler.common.presentation.ui.root.component.RootComponent
import com.artkorchagin.scrubler.common.presentation.ui.root.component.RootContent
import platform.UIKit.UIViewController

//fun MainViewController() = ComposeUIViewController { App(ScrublerSdk(DriverFactory())) }


fun InitKoinDi() = initKoin() //TODO: Remove in future
fun MainViewController(rootComponent: RootComponent): UIViewController {
    // initKoin()
    return ComposeUIViewController {
        RootContent(rootComponent)
        // CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        //    App()
        // }
    }
}


fun buildStoreFactory() = DefaultStoreFactory()