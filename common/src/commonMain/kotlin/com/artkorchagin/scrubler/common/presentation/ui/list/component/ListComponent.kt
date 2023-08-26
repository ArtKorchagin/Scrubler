package com.artkorchagin.scrubler.common.presentation.ui.list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.artkorchagin.scrubler.common.presentation.ui.list.store.ListStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

interface ListComponent {

    val state: StateFlow<ListStore.State>

    val label: Flow<ListStore.Label>

    fun onEvent(intent: ListStore.Intent)

    fun onOutput(output: Output)

    sealed interface Input {
        data class ItemDeleted(val item: String) : Input
    }

    sealed interface Output {
        data class OpenItem(val item: String) : Output
    }
}

class DefaultListComponent(
    componentContext: ComponentContext,
    input2: Flow<ListComponent.Input>,
    private val output: (ListComponent.Output) -> Unit,
) : ListComponent, KoinComponent, ComponentContext by componentContext {

    private val store: ListStore = instanceKeeper.getStore { get { parametersOf(input2) } }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<ListStore.State> = store.stateFlow


    override val label: Flow<ListStore.Label> = store.labels

    override fun onEvent(intent: ListStore.Intent) = store.accept(intent)

    override fun onOutput(output: ListComponent.Output) = output(output)
}