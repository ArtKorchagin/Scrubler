package com.artkorchagin.scrubler.common.presentation.ui.details.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.artkorchagin.scrubler.common.core.coroutines.coroutineScope
import com.artkorchagin.scrubler.common.resources.MR
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext


interface DetailsComponent : CoroutineScope {

    val state: Value<Model>

    fun onDeleteClick()

    data class Model(
        val item: String
    )
}


class DefaultDetailsComponent(
    componentContext: ComponentContext,
    private val itemName: String,
    mainContext: CoroutineContext,
    private val onItemDelete: (String) -> Unit
) : DetailsComponent, ComponentContext by componentContext,
    CoroutineScope by componentContext.coroutineScope(mainContext) {

    override val state: Value<DetailsComponent.Model> = MutableValue(DetailsComponent.Model(itemName))

    override fun onDeleteClick() = onItemDelete(itemName)
}


@Composable
fun DetailsContent(
    component: DetailsComponent,
    modifier: Modifier = Modifier
) {
    val state by component.state.subscribeAsState()

    Column(modifier = modifier.fillMaxSize()) {

        Text(
            text = state.item,
            Modifier.fillMaxWidth()
        )

        Button(
            modifier = modifier.fillMaxWidth().height(80.dp),
            onClick = {
                component.onDeleteClick()
            }
        ) {
            Text(
                text = "Нажать",
                Modifier.fillMaxWidth()
            )
        }

        Image(
            modifier = Modifier.height(120.dp).fillMaxWidth(),
            painter = painterResource(MR.images.ic_movie),
            contentDescription = ""
        )

        KamelImage(
            modifier = Modifier.height(120.dp).fillMaxWidth(),
            contentScale = ContentScale.Crop,
            resource = asyncPainterResource("https://f.vividscreen.info/soft/5986a1e3116efd1189e533300fb15f9a/British-Shorthair-Cat-768x1280.jpg"),
            contentDescription = "Translated description of what the image contains",
            onLoading = { progress -> CircularProgressIndicator(progress) },
            onFailure = {
                Image(
                    painterResource(MR.images.ic_movie),
                    contentDescription = ""
                )
            },
            animationSpec = tween()
        )
    }
}
