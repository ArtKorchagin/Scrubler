package com.artkorchagin.scrubler.common.presentation.ui.movies.search

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt

import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artkorchagin.scrubler.common.data.model.OSFeatureType
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.component.MoviesSearchComponent
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.store.MoviesSearchStore
import com.artkorchagin.scrubler.common.resources.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchMoviesScreen(
    component: MoviesSearchComponent,
    modifier: Modifier = Modifier
) {

    val model by component.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            searchQuery
        ) {
            searchQuery = it
            component.onEvent(MoviesSearchStore.Intent.SearchMovie(it))
        }

        val showProgress = model.loading == MoviesSearchStore.Loading.Screen

        Divider(
            Modifier
                .fillMaxWidth()
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (showProgress) 1f else 0f)
        )


        LazyVerticalGrid(
            // columns = GridCells.Fixed(2),
            columns = GridCells.Adaptive(minSize = 164.dp),
            contentPadding = PaddingValues(8.dp),
            content = {
                items(
                    items = model.movies,
                    key = { it },
                    itemContent = { item ->
                        MovieItem(
                            modifier = modifier.animateItemPlacement(),
                            title = item.attributes.title,
                            imageUrl = item.attributes.imgUrl,
                            type = item.attributes.featureType,
                            year = item.attributes.year
                        )
                    }
                )
            }
        )
    }
}

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    type: OSFeatureType,
    year: String,
    // isSaved
    // onSave
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(CardDefaults.shape)
    ) {
        Column(
            Modifier.fillMaxWidth()
        ) {
            KamelImage(
                modifier = Modifier
                    .aspectRatio(0.68f)
                    .fillMaxWidth()
                    .clip(CardDefaults.shape),
                contentScale = ContentScale.Crop,
                resource = asyncPainterResource(imageUrl),
                contentDescription = null,
                onLoading = { progress ->
                    // TODO: Make shimmer!
                    CircularProgressIndicator(progress)
                },
                onFailure = { exception ->
                    Image(
                        painterResource(MR.images.ic_movie), // TODO: Fail
                        contentDescription = ""
                    )
                },
                animationSpec = tween()
            )

            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    maxLines = 2,
                    //TODO: minLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                val colorBackground = when (type) {
                    OSFeatureType.Episode -> MR.colors.green300
                    OSFeatureType.Movie -> MR.colors.red300
                    OSFeatureType.Tvshow -> MR.colors.blue300
                }

                Row {
                    Text(
                        modifier = Modifier
                            .padding(2.dp)
                            .background(colorResource(colorBackground), shape = ShapeDefaults.Medium),
                        text = type.name,
                        fontSize = 12.sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(2.dp),
                        text = year,
                        fontSize = 12.sp
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,

        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(

            focusedTextColor = MaterialTheme.colorScheme.onBackground, // TODO
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground, // TODO
            //   backgroundColor = Color.White,
            focusedContainerColor = MaterialTheme.colorScheme.background, // TODO
            unfocusedContainerColor = MaterialTheme.colorScheme.background, // TODO

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        //shape = ShapeDefaults,
        leadingIcon = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Поиск"
            )
        },
        trailingIcon = {
            Icon(
                Icons.Outlined.FilterAlt,
                contentDescription = "Настроить Фильтр"
            )
        }

    )
}