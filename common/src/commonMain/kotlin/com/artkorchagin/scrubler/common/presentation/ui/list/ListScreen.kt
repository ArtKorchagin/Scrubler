package com.artkorchagin.scrubler.common.presentation.ui.list


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import kotlin.math.roundToInt

data class ListItem(val id: Int, val text: String)

@Composable
fun SwipeableListWithActions(items: List<ListItem>) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { item ->
            SwipeableListItem(item)
        }
    }
}

@Composable
fun SwipeableListItem(item: ListItem) {
    var offsetX by remember { mutableStateOf(0f) }
    var isSwiping by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX += dragAmount
                    isSwiping = true
                }
            }
    ) {
        if (isSwiping) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // Here you can add your action buttons
                Button(
                    onClick = { /* Handle action 1 */ }) {
                    Text(text = "Action 1")
                }
                Button(onClick = { /* Handle action 2 */ }) {
                    Text(text = "Action 2")
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(8.dp)
        ) {
            Text(text = item.text)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    component: ListComponent,
    modifier: Modifier = Modifier
) {
    // SideEffect {
    //     println("1. Enter to Compose")
    // }
    // // var num by remember { mutableStateOf(0) }
    //
    //
    // SwipeableListWithActions(
    //     (1..100).map {
    //         ListItem(it, "Элемент № $it")
    //     }
    //         .toList()
    // )


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
     val model by component.state.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(items = model.listInfo.orEmpty(), key = { it }) { item ->
                Text(
                    text = item.attributes.title,
                    modifier = modifier
                        .animateItemPlacement()
                        .clickable {
                            component.onOutput(ListComponent.Output.OpenItem(item.attributes.title))
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