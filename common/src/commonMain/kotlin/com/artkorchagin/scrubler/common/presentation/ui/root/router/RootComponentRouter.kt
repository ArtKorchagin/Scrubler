package com.artkorchagin.scrubler.common.presentation.ui.root.router

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.pop
import com.artkorchagin.scrubler.common.presentation.model.MainSection
import com.artkorchagin.scrubler.common.presentation.router.ComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.main.component.DefaultMainComponent
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainComponent
import com.artkorchagin.scrubler.common.presentation.ui.root.component.RootComponent

class RootComponentRouter(componentContext: ComponentContext) : ComponentRouter<RootConfig, RootComponent.Child>(
    componentContext,
    RootConfig::class,
    RootConfig.Main
) {

    companion object {
        const val NAME = "RootComponentRouter"
    }

    override fun createChild(config: RootConfig, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            RootConfig.Main -> RootComponent.Child.MainChild(DefaultMainComponent(componentContext, ::onMainOutput))
        }
    }

    private fun onMainOutput(output: MainComponent.Output) {
        when (output) {
            MainComponent.Output.OnBack -> navigation.pop()
            is MainComponent.Output.OnOpenSection -> {
                when (output.item) {
                    MainSection.Words -> {
                        println("xxx-> open Words")
                    }

                    MainSection.Movies -> {
                        println("xxx-> open Movies")
                    }

                    MainSection.Music -> {
                        println("xxx-> open Music")
                    }

                    MainSection.Youtube -> {
                        println("xxx-> open Youtube")
                    }

                    MainSection.Settings -> {
                        println("xxx-> open Settings")
                    }
                }
            }
        }
    }
}