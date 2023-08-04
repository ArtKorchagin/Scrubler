package com.artkorchagin.scrubler.common.presentation.ui.list

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
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    component: ListComponent,
    modifier: Modifier = Modifier
) {

    val model by component.state.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(items = model.listInfo.orEmpty(), key = { it }) { item ->
                Text(
                    text = item,
                    modifier = modifier
                        .animateItemPlacement()
                        .clickable {
                            component.onOutput(ListComponent.Output.OpenItem(item))
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