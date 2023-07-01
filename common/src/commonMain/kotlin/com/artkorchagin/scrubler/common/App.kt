package com.artkorchagin.scrubler.common


import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.graphics.vector.ImageVector
import com.artkorchagin.scrubler.common.resources.Icons
import com.artkorchagin.scrubler.common.resources.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.rememberImageVector
import org.jetbrains.compose.resources.resource


//TODO: private val appScope = MainScope()

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun App(scrublerSdk: ScrublerSdk) {


    var name by remember { mutableStateOf("") }
    var players by remember { mutableStateOf(scrublerSdk.getPlayers().reversed()) }

    var progress by remember { mutableStateOf(false) }

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement
                .spacedBy(5.dp)
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Input Name") }
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    scrublerSdk.addPlayer(name)
                    players = scrublerSdk.getPlayers().reversed()
                }) {

                Text(stringResource(MR.strings.add_player))
            }

            // Image(
            //     painterResource(MR.images.ic_movie),
            //     contentDescription = ""
            // )

            KamelImage(
                asyncPainterResource("https://f.vividscreen.info/soft/5986a1e3116efd1189e533300fb15f9a/British-Shorthair-Cat-768x1280.jpg"),
                contentDescription = "Translated description of what the image contains",
                onLoading = { progress -> CircularProgressIndicator(progress) },
                onFailure = { exception ->
                    Image(
                        painterResource(MR.images.ic_movie), // TODO: Fail
                        contentDescription = ""
                    )
                },
                animationSpec = tween()
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(MR.colors.themedReferenceColor)
                    ),
                text = "Список Всех Игроков:"
            )

            // Image(
            //     painter = Icons.movie,
            //     contentDescription = "Contact profile picture"
            // )

            if (progress) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterHorizontally),
                )
            } else {


                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(players, key = { it.index }) {
                        Card(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .animateItemPlacement()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                text = "${it.index}:${it.name}"
                            )
                        }
                    }
                }
            }
        }
    }


    // appScope.launch {
    //     delay(2000)
    //     progress = false
    // }
}