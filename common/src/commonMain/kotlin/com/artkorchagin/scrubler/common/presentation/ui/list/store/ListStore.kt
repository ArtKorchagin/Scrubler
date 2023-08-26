package com.artkorchagin.scrubler.common.presentation.ui.list.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.artkorchagin.scrubler.common.data.model.OSFeature
import com.artkorchagin.scrubler.common.data.repository.WordsRepository
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMviKotlinApi::class)
class ListStore(
    storeFactory: StoreFactory,
    repository: WordsRepository,
    input: Flow<ListComponent.Input>
) : Store<ListStore.Intent, ListStore.State, ListStore.Label> by storeFactory.create<Intent, Action, Msg, State, Label>(

    name = "ListStore",
    initialState = State(),
    bootstrapper = coroutineBootstrapper {

        launch {

            input.onEach {
                when (it) {
                    is ListComponent.Input.ItemDeleted -> dispatch(Action.ItemDeleted(it.item))
                }
            }.launchIn(this)

            dispatch(Action.LoadData) // Dispatch an Action
        }
    },

    // USE LABEL
    executorFactory = coroutineExecutorFactory {

        onAction<Action.ItemDeleted> {
            dispatch(Msg.Loading(true))
            launch {
                repository.removeItem(it.item)
                dispatch(Msg.Value(repository.getFeatures("Crown")))
                dispatch(Msg.Loading(false))
            }
        }

        onAction<Action.LoadData> {
            dispatch(Msg.Loading(true))
            launch {
                dispatch(Msg.Value(repository.getFeatures("Crown")))
                dispatch(Msg.Loading(false))
            }
        }

        onIntent<Intent.SaveItem> { intent ->
            dispatch(Msg.Loading(true))
            launch {
                withContext(Dispatchers.Default) { //TODO: IO
                    delay(3_000)
                }
                dispatch(Msg.Loading(false))
            }
        }

    },
    reducer = { msg ->
        when (msg) {
            is Msg.Error -> copy(error = msg.error)
            is Msg.Loading -> copy(isLoading = msg.isLoading)
            is Msg.Value -> copy(listInfo = msg.items)
        }
    }

) {
    init {
        println("xxx-> Create ListStore ")
    }

    sealed class Intent {
        data class SaveItem(val item: String) : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val listInfo: List<OSFeature>? = null
    )

    private sealed interface Action {
        data class ItemDeleted(val item: String) : Action
        object LoadData : Action
    }

    private sealed interface Msg {
        data class Loading(val isLoading: Boolean) : Msg
        data class Error(val error: String?) : Msg
        data class Value(val items: List<OSFeature>) : Msg
    }

    sealed interface Label {
        // ...
    }
}