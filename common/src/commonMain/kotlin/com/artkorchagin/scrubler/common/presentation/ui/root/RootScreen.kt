package com.artkorchagin.scrubler.common.presentation.ui.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.artkorchagin.scrubler.common.presentation.theme.ScrublerTheme
import com.artkorchagin.scrubler.common.presentation.ui.main.MainScreen
import com.artkorchagin.scrubler.common.presentation.ui.root.component.RootComponent

// @Composable
// fun App(appDeclaration: KoinAppDeclaration = {}) {
//     KoinApplication(application = {
//         appDeclaration()
//         // Koin configuration here
//     }) {
//
//     }
// }

@Composable
fun RootScreen(component: RootComponent, modifier: Modifier = Modifier) {
    // fun RootContent(component: RootComponent = koinInject(), modifier: Modifier = Modifier) {

    ScrublerTheme {
        //TODO

        Surface(
            modifier = modifier.fillMaxSize(),
            shadowElevation = 3.dp,
        ) {
            Children(
                stack = component.stack,
                // modifier = modifier.padding(paddingValues),
                animation = stackAnimation(slide())
            ) {
                when (val child = it.instance) {
                    is RootComponent.Child.MainChild -> MainScreen(component = child.component)
                }
            }
        }

    }
}