package com.artkorchagin.scrubler.common.presentation.router

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import kotlin.reflect.KClass

abstract class ComponentRouter<Config : Parcelable, Child : Any>(
    componentContext: ComponentContext,
    configClass: KClass<Config>,
    initialConfig: Config
) {

    protected val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<*, Child>> by lazy {
        componentContext.childStack(
            source = navigation,
            initialStack = { listOf(initialConfig) },
            configurationClass = configClass,
            handleBackButton = true,
            childFactory = ::createChild
        )
    }

    abstract fun createChild(config: Config, componentContext: ComponentContext): Child
}