package com.artkorchagin.scrubler.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfig>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = RootConfig.List,
            handleBackButton = true,
            childFactory = ::child
        )

    private fun child(config: RootConfig, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            RootConfig.List -> RootComponent.Child.ListChild(listComponent(componentContext))
            is RootConfig.Details -> RootComponent.Child.DetailsChild(detailsComponent(componentContext, config.item))
            else -> throw RuntimeException("Unknown config")
        }
    }

    private fun detailsComponent(componentContext: ComponentContext, item: String): DetailsComponent {
        return DefaultDetailsComponent(componentContext, item)
    }

    private fun listComponent(componentContext: ComponentContext): ListComponent {
        return DefaultListComponent(componentContext) { item ->
            navigation.push(RootConfig.Details(item))
        }
    }

    override fun onBackClicked(toIndex: Int) {
        //TODO: toIndex
        navigation.pop()
    }
}

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.DetailsChild -> DetailsContent(component = child.component)
            is RootComponent.Child.ListChild -> ListContent(component = child.component)
        }
    }
}