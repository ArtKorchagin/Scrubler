package com.artkorchagin.scrubler.common.presentation.ui.main.router

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.artkorchagin.scrubler.common.presentation.router.ComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DefaultDetailsComponent
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DetailsComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.DefaultListComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainComponent
import kotlinx.coroutines.flow.MutableSharedFlow

class MainComponentRouter(componentContext: ComponentContext) : ComponentRouter<MainConfig, MainComponent.Child>(
    componentContext,
    MainConfig::class,
    MainConfig.List
) {

    companion object {
        const val NAME = "MainComponentRouter"
    }

    private val listInput = MutableSharedFlow<ListComponent.Input>(extraBufferCapacity = Int.MAX_VALUE)

    override fun createChild(config: MainConfig, componentContext: ComponentContext): MainComponent.Child =
        when (config) {
            MainConfig.List -> MainComponent.Child.ListChild(
                DefaultListComponent(componentContext, listInput, ::onListOutput)
            )

            is MainConfig.Details -> MainComponent.Child.DetailsChild(
                DefaultDetailsComponent(componentContext, config.item, ::onDetailsOutput)
            )
        }

    // Outputs

    private fun onDetailsOutput(output: DetailsComponent.Output) = when (output) {
        is DetailsComponent.Output.DeleteItem -> {
            navigation.pop {
                println("xxx-> instance=${stack.value.active.instance}")
                listInput.tryEmit(ListComponent.Input.ItemDeleted(output.item))
            }
        }
    }

    private fun onListOutput(output: ListComponent.Output) = when (output) {
        is ListComponent.Output.OpenItem -> navigation.push(MainConfig.Details(output.item))
    }
}