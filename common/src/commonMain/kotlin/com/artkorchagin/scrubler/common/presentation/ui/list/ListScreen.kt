package com.artkorchagin.scrubler.common.presentation.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import com.artkorchagin.scrubler.common.resources.MR
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.cache
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role.Companion.Image
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import dev.icerock.moko.resources.compose.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    component: ListComponent,
    modifier: Modifier = Modifier
) {
    SideEffect {
        println("1. Enter to Compose")
    }
    // var num by remember { mutableStateOf(0) }


    Box {
        var imageHeightPx by remember { mutableStateOf(0) }

        Image(
            painter = painterResource(MR.images.ic_movie),
            contentDescription = "I'm above the text",
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    // SideEffect {
                    println("2. onSizeChanged = $size")
                    // }

                    // Don't do this
                    imageHeightPx = size.height
                }
        )

        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(
                top = with(LocalDensity.current) {
                    println("3. add Padding = $imageHeightPx")
                    imageHeightPx.toDp()
                }
            )
        )
    }


    // Column(
    //     modifier = modifier.fillMaxWidth()
    // ) {
    //     SideEffect {
    //         println("2. Add Text Hello")
    //     }
    //     Text("Hello Test Compose $num")
    //
    //     Button(onClick = {
    //         println("3. Click On Button")
    //         num++
    //     }) {
    //         SideEffect {
    //             println("4. Add Text Increase")
    //         }
    //         Text("increase")
    //     }
    // }

    // currentComposer.cache()
    //  val model by component.state.collectAsState()
    // Box(modifier = modifier.fillMaxSize()) {
    //     LazyColumn {
    //         items(items = model.listInfo.orEmpty(), key = { it }) { item ->
    //             Text(
    //                 text = item,
    //                 modifier = modifier
    //                     .animateItemPlacement()
    //                     .clickable {
    //                         component.onOutput(ListComponent.Output.OpenItem(item))
    //                     },
    //             )
    //         }
    //     }
    //
    //     if (model.isLoading) {
    //         CircularProgressIndicator(
    //             modifier = Modifier.align(Alignment.Center)
    //         )
    //     }
    // }
}