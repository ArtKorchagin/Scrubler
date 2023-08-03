package com.artkorchagin.scrubler.common.presentation.mvi.store

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.artkorchagin.scrubler.common.presentation.mvi.store.TestStore.Intent
import com.artkorchagin.scrubler.common.presentation.mvi.store.TestStore.Label
import com.artkorchagin.scrubler.common.presentation.mvi.store.TestStore.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface TestStore : Store<Intent, State, Label> {

    sealed interface Intent {
        object Increment : Intent
        object Decrement : Intent
        data class Sum(val n: Int) : Intent
    }

    @Parcelize
    data class State(val value: Int) : Parcelable

    sealed interface Label {
        val ik: InstanceKeeper
    }
}


internal class TestStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(stateKeeper: StateKeeper, startSum: Int): TestStore =
        object : TestStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CalculatorStore",
            initialState = stateKeeper.consume("TestStoreState") ?: State(value = 0),
            bootstrapper = BootstrapperImpl(startSum),
            // bootstrapper = SimpleBootstrapper(Action.Sum(startSum)),
            executorFactory = TestStoreFactory::ExecutorImpl,
            reducer = ReducerImpl
        ) {
            val storeMember = "" // Just to fulfil curiosity
        }.also {

            stateKeeper.register("TestStoreState") {
                // Вызывается при сохранении состояния
                it.state.copy(value = 0)
            }
        }

    private sealed interface Action {
        data class SetValue(val value: Int) : Action
    }

    private sealed interface Msg {
        class Value(val value: Int) : Msg
    }

    // TODO:!!
    private class BootstrapperImpl(val n: Int) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                withContext(Dispatchers.Default) {
                    val sum = (0..n).sum()
                    dispatch(Action.SetValue(sum))
                }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.SetValue -> dispatch(Msg.Value(action.value))
            }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.Decrement -> dispatch(Msg.Value(getState().value - 1))
                Intent.Increment -> dispatch(Msg.Value(getState().value + 1))
                is Intent.Sum -> sum(intent.n)
            }
        }

        private fun sum(n: Int) {
            scope.launch {
                withContext(Dispatchers.Default) {
                    val sum = (0..n).sum()
                    dispatch(Msg.Value(sum))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State =
            when (message) {
                is Msg.Value -> copy(value = message.value)
            }
    }
}


