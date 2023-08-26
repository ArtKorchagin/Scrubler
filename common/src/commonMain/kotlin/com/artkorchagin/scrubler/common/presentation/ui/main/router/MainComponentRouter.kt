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
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.component.DefaultMoviesListComponent
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.component.MoviesListComponent
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.component.DefaultMoviesSearchComponent
import kotlinx.coroutines.flow.MutableSharedFlow

class MainComponentRouter(componentContext: ComponentContext) : ComponentRouter<MainConfig, MainComponent.Child>(
    componentContext,
    MainConfig::class,
    MainConfig.MoviesList //TODO
) {

    companion object {
        const val NAME = "MainComponentRouter"
    }

    private val listInput = MutableSharedFlow<ListComponent.Input>(extraBufferCapacity = Int.MAX_VALUE)

    override fun createChild(config: MainConfig, componentContext: ComponentContext): MainComponent.Child =
        when (config) {
            MainConfig.List -> MainComponent.Child.List(
                DefaultListComponent(componentContext, listInput, ::onListOutput)
            )

            is MainConfig.Details -> MainComponent.Child.Details(
                DefaultDetailsComponent(componentContext, config.item, ::onDetailsOutput)
            )

            is MainConfig.MoviesSearch -> MainComponent.Child.MoviesSearch(
                DefaultMoviesSearchComponent(componentContext)
            )

            is MainConfig.MoviesList -> MainComponent.Child.MoviesList(
                DefaultMoviesListComponent(componentContext, ::onMoviesListOutput)
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

    private fun onMoviesListOutput(output: MoviesListComponent.Output) = when (output) {
        MoviesListComponent.Output.SearchMovie -> navigation.push(MainConfig.MoviesSearch)
    }

    private fun onListOutput(output: ListComponent.Output) = when (output) {
        is ListComponent.Output.OpenItem -> navigation.push(MainConfig.Details(output.item))
    }
}