package com.artkorchagin.scrubler.common.presentation.ui.movies.list.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.artkorchagin.scrubler.common.data.model.Movie
import com.artkorchagin.scrubler.common.data.repository.WordsRepository
import kotlinx.coroutines.launch

/**
 * @author Arthur Korchagin (arth.korchagin@gmail.com)
 * @since 25.08.2023
 */

class MoviesListStore(
    storeFactory: StoreFactory,
    repository: WordsRepository
) : Store<MoviesListStore.Intent, MoviesListStore.State, Nothing>
by storeFactory.create<Intent, Unit, Message, State, Nothing>(
    name = "MoviesListStore",
    initialState = State(),
    bootstrapper = SimpleBootstrapper(Unit),
    executorFactory = coroutineExecutorFactory {
        onAction<Unit> {
            dispatch(Message.Loading(true))
            launch {
                val movies = repository.getMovies()
                dispatch(Message.Value(movies))
                dispatch(Message.Loading(false))
            }
        }

        onIntent<Intent.RemoveMovie> {
            //TODO:
        }

    },
    reducer = { message ->
        when (message) {
            is Message.Error -> copy(error = message.error)
            is Message.Loading -> copy(loading = message.isLoading)
            is Message.Value -> copy(movies = message.movies)
        }

    }
) {

    sealed class Intent {
        data class RemoveMovie(val id: String) : Intent()
    }

    data class State(
        val loading: Boolean = true,
        val error: String? = null,
        val movies: List<Movie> = emptyList()
    )

    private sealed interface Message {
        data class Loading(val isLoading: Boolean) : Message
        data class Error(val error: String) : Message
        data class Value(val movies: List<Movie>) : Message
    }
}