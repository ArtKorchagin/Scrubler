package com.artkorchagin.scrubler.common.presentation.ui.root.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.MovieCreation
import androidx.compose.material.icons.rounded.MusicVideo
import androidx.compose.material.icons.rounded.YoutubeSearchedFor
import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.artkorchagin.scrubler.common.presentation.router.ComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.root.router.RootComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.root.router.RootConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by componentContext {

    private val router = get<ComponentRouter<RootConfig, RootComponent.Child>>(
        named(RootComponentRouter.NAME)
    ) { parametersOf(componentContext) }

    override val stack: Value<ChildStack<*, RootComponent.Child>> = router.stack
}