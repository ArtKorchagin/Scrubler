package com.artkorchagin.scrubler.common.presentation.ui.details

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.artkorchagin.scrubler.common.data.model.TestItem
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DetailsComponent
import com.artkorchagin.scrubler.common.resources.MR
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun DetailsScreen(
    component: DetailsComponent, //TODO: DI
    modifier: Modifier = Modifier
) {
    val state by component.state.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        println("xxx-> item=$state.item")
        state.item?.let { item ->
            DetailsInfo(item, component::onOutput)
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun DetailsInfo(
    info: TestItem,
    onOutput: (DetailsComponent.Output) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = info.info,
            Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier.fillMaxWidth().size(48.dp),
            onClick = { onOutput(DetailsComponent.Output.DeleteItem(info.name)) }
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