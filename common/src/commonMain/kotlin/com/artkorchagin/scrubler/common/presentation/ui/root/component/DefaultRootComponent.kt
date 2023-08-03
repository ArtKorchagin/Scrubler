package com.artkorchagin.scrubler.common.presentation.ui.root.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.MovieCreation
import androidx.compose.material.icons.rounded.MusicVideo
import androidx.compose.material.icons.rounded.YoutubeSearchedFor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.artkorchagin.scrubler.common.presentation.ui.main.component.DefaultMainComponent
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainComponent
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainContent
import com.artkorchagin.scrubler.common.presentation.theme.ScrublerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.KoinAppDeclaration

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {


    //TODO: private val mainContext = SupervisorJob() + Dispatchers.Main
    private val navigation = StackNavigation<RootConfig>()


    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = RootConfig.Main,
            handleBackButton = true,
            childFactory = ::createChild
        )

    // TODO: koin!
    private fun createChild(config: RootConfig, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            RootConfig.Main -> RootComponent.Child.MainChild(mainComponent(componentContext)) //TODO: get()
        }
    }

    private fun mainComponent(componentContext: ComponentContext): MainComponent {
        return DefaultMainComponent(componentContext)
    }

    override fun onBackClicked(toIndex: Int) {
        //TODO: toIndex
        navigation.pop()
    }
}

data class SelectedItem(val title: String, val image: ImageVector)

val items = listOf(
    SelectedItem("Слова", Icons.Rounded.EditNote),
    SelectedItem("Фильмы", Icons.Rounded.MovieCreation),
    SelectedItem("Музыка", Icons.Rounded.MusicVideo),
    SelectedItem("Youtube", Icons.Rounded.YoutubeSearchedFor),
    SelectedItem("Настройки", Icons.Rounded.EditNote)
)


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
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
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
                    is RootComponent.Child.MainChild -> MainContent(component = child.component)
                }
            }
        }

    }
}