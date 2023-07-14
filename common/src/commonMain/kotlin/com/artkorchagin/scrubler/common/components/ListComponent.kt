package com.artkorchagin.scrubler.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue


interface ListComponent {

    val model: Value<Model>

    fun onItemClicked(item: String)

    data class Model(
        val items: List<String>
    )
}

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (String) -> Unit
) : ListComponent, ComponentContext by componentContext {

    override val model: Value<ListComponent.Model> =
        MutableValue(ListComponent.Model(items = List(100) { "Item $it" }))

    override fun onItemClicked(item: String) = onItemSelected(item)
}


@Composable
fun ListContent(component: ListComponent, modifier: Modifier = Modifier) {

    val model by component.model.subscribeAsState()

    LazyColumn {
        items(items = model.items) { item ->
            Text(
                text = item,
                modifier = Modifier.clickable {
                    component.onItemClicked(item = item)
                },
            )
        }
    }

}