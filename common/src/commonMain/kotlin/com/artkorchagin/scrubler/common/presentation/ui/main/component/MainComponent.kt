package com.artkorchagin.scrubler.common.presentation.ui.main.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.artkorchagin.scrubler.common.presentation.model.MainSection
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DetailsComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent

interface MainComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onOutput(output: Output)

    sealed class Child {
        class ListChild(val component: ListComponent) : Child()
        class DetailsChild(val component: DetailsComponent) : Child()
    }

    sealed interface Output {
        class OnOpenSection(val item: MainSection) : Output
        object OnBack : Output
    }
}