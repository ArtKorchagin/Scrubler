package com.artkorchagin.scrubler.common.presentation.ui.details.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.artkorchagin.scrubler.common.data.model.TestItem
import com.artkorchagin.scrubler.common.data.repository.WordsRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMviKotlinApi::class)
class DetailsStore(
    storeFactory: StoreFactory,
    repository: WordsRepository,
    item: String
) : Store<DetailsStore.Intent, DetailsStore.State, DetailsStore.Label> by storeFactory
    .create<Intent, Action, Msg, State, Label>(
        name = "DetailsStore",
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Action.LoadItemInfo(item)),
        executorFactory = coroutineExecutorFactory {

            onAction<Action.LoadItemInfo> {
                dispatch(Msg.Loading(true))
                launch {
                    dispatch(Msg.Value(repository.getItemInfo(item)))
                    dispatch(Msg.Loading(false))
                }
            }
        },
        reducer = { msg ->
            when(msg) {
                is Msg.Error -> copy(error = msg.error)
                is Msg.Loading -> copy(isLoading = msg.isLoading)
                is Msg.Value -> copy(item = msg.item)
            }
        }
    ) {

    init {
        println("xxx-> Create DetailsStore ")
    }

    sealed class Intent {
        //TODO: ?? data class SaveItem(val item: String) : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val item: TestItem? = null,
    )

    private sealed interface Action {
        data class LoadItemInfo(val item: String) : Action
    }

    private sealed interface Msg {
        data class Loading(val isLoading: Boolean) : Msg
        data class Error(val error: String?) : Msg
        data class Value(val item: TestItem) : Msg
    }

    sealed interface Label {
        // ...
    }
}