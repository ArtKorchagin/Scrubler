package com.artkorchagin.scrubler.common.presentation.ui.list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.artkorchagin.scrubler.common.core.coroutines.coroutineScope
import com.artkorchagin.scrubler.common.presentation.ui.list.store.Intent
import com.artkorchagin.scrubler.common.presentation.ui.list.store.ListStore
import com.artkorchagin.scrubler.common.presentation.ui.list.store.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.coroutines.CoroutineContext

interface ListComponent {

    val state: StateFlow<State>
    fun onItemDeleted(item: String)
    fun onItemClicked(item: String)

    // data class Model(
    //     val items: List<String>
    // )

    sealed interface Input {
        data class ItemDeleted(val item: String) : Input
    }
}

class DefaultListComponent(
    componentContext: ComponentContext,
    input: Flow<ListComponent.Input>,
    mainContext: CoroutineContext,
    private val onItemSelected: (String) -> Unit,

    ) : ListComponent,
    KoinComponent,
    ComponentContext by componentContext,
    CoroutineScope by componentContext.coroutineScope(mainContext) {

    private val store: ListStore = instanceKeeper.getStore { get() }

    // private var _model = MutableValue(ListComponent.Model(items = List(100) { "Item $it" }))
    // override val model: Value<ListComponent.Model> = _model

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow

    init {
        input
            .onEach {
                when (it) {
                    is ListComponent.Input.ItemDeleted -> onItemDeleted(it.item)
                }
            }
            .launchIn(this)
    }

    override fun onItemDeleted(item: String) {
        //TODO!!!
        store.accept(Intent.SaveItem(item))
        // _model.update {
        //     it.copy(items = it.items.filterNot { it == item })
        // }
    }

    override fun onItemClicked(item: String) = onItemSelected(item)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListContent(component: ListComponent, modifier: Modifier = Modifier) {

    val model by component.state.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(items = model.listInfo.orEmpty(), key = { it }) { item ->
                Text(
                    text = item,
                    modifier = modifier
                        .animateItemPlacement()
                        .clickable {
                            component.onItemClicked(item = item)
                        },
                )
            }
        }

        if (model.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}