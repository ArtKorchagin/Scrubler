package com.artkorchagin.scrubler.common.presentation.ui.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)


    sealed class Child {
        class MainChild(val component: MainComponent) : Child()
    }
}