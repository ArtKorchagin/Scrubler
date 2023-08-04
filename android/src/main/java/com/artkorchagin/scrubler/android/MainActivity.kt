package com.artkorchagin.scrubler.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.artkorchagin.scrubler.common.presentation.ui.root.RootScreen
import com.artkorchagin.scrubler.common.presentation.ui.root.component.DefaultRootComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )
        setContent {
            // MaterialTheme {
            //     Surface {
            RootScreen(component = root, modifier = Modifier.fillMaxSize())
            // }

            // App(ScrublerSdk(DriverFactory(this)))
            // }
        }
    }
}