package com.artkorchagin.scrubler.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.Button
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

@Composable
fun App(scrublerSdk: ScrublerSdk) {

    var name by remember { mutableStateOf("") }
    val count = remember { mutableStateOf(0) }

    var players by remember { mutableStateOf(scrublerSdk.getPlayers()) }

   // val platformName = getPlatformName()

    MaterialTheme {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement =
            Arrangement.spacedBy(5.dp)

        ) {

            TextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Input Name") }
            )

            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    count.value = 0
                }) {
                Text("Reset")
            }

            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    scrublerSdk.addPlayer(name)
                    players = scrublerSdk.getPlayers()
                }) {
                Text("Add Player")
            }

            LazyColumn {
                item {
                    Text(text = "Список Всех Игроков")
                }
                items(players) {
                    Text(text = "${it.index}:${it.name}")
                }
            }
        }
    }
}
