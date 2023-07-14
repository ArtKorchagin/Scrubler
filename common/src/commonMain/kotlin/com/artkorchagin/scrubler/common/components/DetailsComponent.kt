package com.artkorchagin.scrubler.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import androidx.compose.runtime.getValue


interface DetailsComponent {

    val state: Value<Model>

    data class Model(
        val item: String
    )
}


class DefaultDetailsComponent(
    componentContext: ComponentContext,
    itemName: String,
) : DetailsComponent, ComponentContext by componentContext {

    override val state: Value<DetailsComponent.Model> = MutableValue(DetailsComponent.Model(itemName))
}


@Composable
fun DetailsContent(component: DetailsComponent, modifier: Modifier = Modifier) {
    val state by component.state.subscribeAsState()
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = state.item,
            Modifier.fillMaxWidth()
        )
    }
}