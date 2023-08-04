import androidx.compose.ui.window.Window
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.artkorchagin.scrubler.common.di.initKoin
import com.artkorchagin.scrubler.common.presentation.ui.root.RootScreen
import com.artkorchagin.scrubler.common.presentation.ui.root.component.DefaultRootComponent
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.Document

fun main() {
    initKoin()

    val lifecycle = LifecycleRegistry()
    val rootComponent = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
        // ,
        // storeFactory = DefaultStoreFactory(),
    )
    lifecycle.attachToDocument()

    onWasmReady {
        Window("Scrubler") {
            RootScreen(rootComponent)
        }
    }
}

private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (document.visibilityState == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()
    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

private val Document.visibilityState: String
    get() = asDynamic().visibilityState.unsafeCast<String>()