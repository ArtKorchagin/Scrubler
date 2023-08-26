package com.artkorchagin.scrubler.common.presentation.ui.movies.search.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.artkorchagin.scrubler.common.data.model.OSFeature
import com.artkorchagin.scrubler.common.data.repository.WordsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Arthur Korchagin (arth.korchagin@gmail.com)
 * @since 24.08.2023
 */

private const val REQUEST_THROTTLING = 1000L

class MoviesSearchStore(
    storeFactory: StoreFactory,
    repository: WordsRepository
) : Store<MoviesSearchStore.Intent, MoviesSearchStore.State, Nothing>
by storeFactory.create<Intent, Unit, Message, State, Nothing>(
    name = "MoviesStore",
    initialState = State(),
    bootstrapper = SimpleBootstrapper(Unit),
    executorFactory = coroutineExecutorFactory {

        var searchJob: Job? = null
        onIntent<Intent.SearchMovie> { intent ->
            searchJob?.cancel()
            dispatch(Message.Loading(true))
            searchJob = launch {
                delay(REQUEST_THROTTLING)
                try {
                    val movies = repository.getFeatures(intent.query)
                    dispatch(Message.Value(movies))
                } catch (e: Throwable) { //TODO: Make different handling
                    e.printStackTrace()
                    println("Error -> $e")
                    dispatch(Message.Error(e.message ?: "Unknown Error"))
                }
                dispatch(Message.Loading(false))
            }
        }

        onIntent<Intent.SaveMovieSubtitles> { intent ->
            dispatch(Message.Loading(true))
            launch {
                //TODO
                dispatch(Message.Loading(false))
            }

        }
    },
    reducer = { message ->
        when (message) {
            is Message.Loading -> copy(loading = if (message.isLoading) Loading.Screen else Loading.No) //TODO To do something with that
            is Message.SavingMovie -> copy(loading = Loading.SavingMovie(message.movieId))
            is Message.Error -> copy(error = message.error)
            is Message.Value -> copy(movies = message.movies)
        }
    }

) {

    sealed class Intent {
        data class SearchMovie(val query: String) : Intent()

        data class SaveMovieSubtitles(val id: String) : Intent()
    }

    data class State(
        val loading: Loading = Loading.No,
        val error: String? = null,
        val movies: List<OSFeature> = emptyList()
    )

    sealed interface Loading {
        object No : Loading
        object Screen : Loading
        data class SavingMovie(val movieId: String) : Loading
    }

    private sealed interface Message {
        data class SavingMovie(val movieId: String) : Message
        data class Loading(val isLoading: Boolean) : Message
        data class Error(val error: String) : Message
        data class Value(val movies: List<OSFeature>) : Message
    }
}