package com.artkorchagin.scrubler.common.presentation.ui.movies.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.component.MoviesListComponent

/**
 * @author Arthur Korchagin (arth.korchagin@gmail.com)
 * @since 25.08.2023
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesListScreen(
    component: MoviesListComponent,
    modifier: Modifier = Modifier
) {

    val model by component.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            item {
                SearchBar(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    component.onOutput(MoviesListComponent.Output.SearchMovie)
                }
            }
            items(
                items = model.movies,
                key = { it }
            ) { item ->
                MovieItem(
                    modifier = modifier.animateItemPlacement(),
                    title = item.title,
                    description = "",
                    imageUrl = ""
                )
            }

        }

        if (model.loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageUrl: String,
    // onRemove
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(CardDefaults.shape)
    ) {

        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = title + description + imageUrl
            )
        }

    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onOpenSearch: () -> Unit,
) {

    Row(
        modifier = modifier
            .height(40.dp)
            .background(MaterialTheme.colorScheme.surface, ShapeDefaults.ExtraLarge)
            .clickable { onOpenSearch() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(24.dp),

            imageVector = Icons.Filled.Search,
            contentDescription = null
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = "Поиск фильмов..."
        )
    }
}