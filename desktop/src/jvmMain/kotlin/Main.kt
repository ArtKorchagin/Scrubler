import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.artkorchagin.scrubler.common.di.initKoin
import com.artkorchagin.scrubler.common.presentation.ui.root.RootScreen
import com.artkorchagin.scrubler.common.presentation.ui.root.component.DefaultRootComponent

@OptIn(ExperimentalDecomposeApi::class)
fun main() {

    val lifecycle = LifecycleRegistry() // TODO: Move to DI

    val root = runOnUiThread {
        initKoin()
        DefaultRootComponent(componentContext = DefaultComponentContext(lifecycle = lifecycle))
    }

    application {
        val windowState = rememberWindowState(width = 480.dp, height = 640.dp)

        LifecycleController(lifecycle, windowState)

        Window(
            title = "Scrubler ${windowState.size.width.value}",
            onCloseRequest = ::exitApplication,
            state = windowState
        ) {
            // MaterialTheme {
            //     Surface {
            RootScreen(component = root, modifier = Modifier.fillMaxSize())
            // }
            // }
            // TODO App(ScrublerSdk(DriverFactory()))
        }
    }
}
