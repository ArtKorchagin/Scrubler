package com.artkorchagin.scrubler.common.presentation.ui.details.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.artkorchagin.scrubler.common.presentation.ui.details.store.DetailsStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf


interface DetailsComponent {

    val state: StateFlow<DetailsStore.State>

    // fun onEvent(intent: Intent)

    fun onOutput(output: Output)

    sealed interface Output {
        data class DeleteItem(val item: String) : Output
    }
}


class DefaultDetailsComponent(
    componentContext: ComponentContext,
    private val itemName: String,
    private val output: (DetailsComponent.Output) -> Unit,
) : DetailsComponent, KoinComponent, ComponentContext by componentContext {

    private val store: DetailsStore = instanceKeeper.getStore { get { parametersOf(itemName) } }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state = store.stateFlow

    override fun onOutput(output: DetailsComponent.Output) = output(output)
}