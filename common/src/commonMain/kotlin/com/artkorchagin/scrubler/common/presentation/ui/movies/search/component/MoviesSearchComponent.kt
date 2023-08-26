@file:OptIn(ExperimentalCoroutinesApi::class)

package com.artkorchagin.scrubler.common.presentation.ui.movies.search.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.store.MoviesSearchStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

interface MoviesSearchComponent {

    val state: StateFlow<MoviesSearchStore.State>

    fun onEvent(intent: MoviesSearchStore.Intent)

    // fun onOutput(output: Output)

    // sealed interface Output {
    //     data class SaveMovie(val movieId: String) : Output
    // }
}


class DefaultMoviesSearchComponent(
    componentContext: ComponentContext,
    // private val output: (SearchMoviesComponent.Output) -> Unit
) : MoviesSearchComponent, KoinComponent, ComponentContext by componentContext {

    private val store: MoviesSearchStore = instanceKeeper.getStore { get() }

    override val state: StateFlow<MoviesSearchStore.State> = store.stateFlow
    override fun onEvent(intent: MoviesSearchStore.Intent) = store.accept(intent)
    //override fun onOutput(output: SearchMoviesComponent.Output) = output(output)
}