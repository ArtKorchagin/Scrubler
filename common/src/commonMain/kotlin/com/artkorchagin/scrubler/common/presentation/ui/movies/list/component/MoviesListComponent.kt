@file:OptIn(ExperimentalCoroutinesApi::class)

package com.artkorchagin.scrubler.common.presentation.ui.movies.list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.store.MoviesListStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * @author Arthur Korchagin (arth.korchagin@gmail.com)
 * @since 25.08.2023
 */

interface MoviesListComponent {

    val state: StateFlow<MoviesListStore.State>

    fun onEvent(intent: MoviesListStore.Intent)

    fun onOutput(output: Output)

    sealed interface Output {
        object SearchMovie : Output
    }
}

class DefaultMoviesListComponent(
    componentContext: ComponentContext,
    private val output: (MoviesListComponent.Output) -> Unit
) : MoviesListComponent, KoinComponent, ComponentContext by componentContext {

    private val store: MoviesListStore = instanceKeeper.getStore { get() }

    override val state: StateFlow<MoviesListStore.State> = store.stateFlow
    override fun onEvent(intent: MoviesListStore.Intent) = store.accept(intent)
    override fun onOutput(output: MoviesListComponent.Output) = output(output)
}