package com.artkorchagin.scrubler.common.presentation.ui.list.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.artkorchagin.scrubler.common.data.repository.WordsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// interface ListStore : Store<ListStore.Intent, ListStore.State, Nothing> {


sealed class Intent {
    data class SaveItem(val item: String) : Intent()
}

data class State(
    val isLoading: Boolean = false,
    val error: String? = null,
    val listInfo: List<String>? = null
)
// }

private sealed interface Action {
    object Initialize : Action
}

private sealed interface Msg {
    data class Loading(val isLoading: Boolean) : Msg
    data class Error(val error: String?) : Msg
    data class Value(val items: List<String>) : Msg
}

internal sealed interface Label {
    // ...
}

@OptIn(ExperimentalMviKotlinApi::class)
internal class ListStore(
    storeFactory: StoreFactory,
    repository: WordsRepository
) : Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(

    name = "ListStore",
    initialState = State(),
    bootstrapper = coroutineBootstrapper {
        launch {
            // Launch a coroutine
            // val sum = withContext(Dispatchers.Default) { (1L..1000000.toLong()).sum() }
            // TODO:
            dispatch(Action.Initialize) // Dispatch an Action
        }
    },

    executorFactory = coroutineExecutorFactory {

        onAction<Action.Initialize> {
            dispatch(Msg.Loading(true))
            launch {
                val items = withContext(Dispatchers.Default) { //TODO: IO
                    delay(3_000)
                    List(100) { "Item $it ${repository.getTestString()}" }
                }
                dispatch(Msg.Loading(false))
                dispatch(Msg.Value(items))
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

)