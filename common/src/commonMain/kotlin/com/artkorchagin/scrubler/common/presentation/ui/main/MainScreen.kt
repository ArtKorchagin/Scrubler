package com.artkorchagin.scrubler.common.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.artkorchagin.scrubler.common.presentation.dialogs.DialogUnderConstruction
import com.artkorchagin.scrubler.common.presentation.model.MainSection
import com.artkorchagin.scrubler.common.presentation.ui.details.DetailsScreen
import com.artkorchagin.scrubler.common.presentation.ui.list.ListScreen
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainComponent
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.MoviesListScreen
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.SearchMoviesScreen
import dev.icerock.moko.resources.compose.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(component: MainComponent, modifier: Modifier = Modifier) {

    var showUnderConstruction by remember { mutableStateOf(false) }
    var snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    // val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selectedItem by remember { mutableStateOf(0) }
   // Icon(Icons.Filled.Movie, contentDescription = null)
    // ModalNavigationDrawer(
    //
    //     drawerState = drawerState,
    //     drawerContent = {
    //         ModalDrawerSheet(
    //             drawerShape = RectangleShape
    //         ) {
    //             Text("Drawer title", modifier = Modifier.padding(16.dp))
    //             Divider()
    //             NavigationDrawerItem(
    //                 shape = RectangleShape,
    //                 icon = { Icon(Icons.Filled.Movie, contentDescription = null) },
    //                 label = { Text("Учить слова по фильмам") },
    //                 selected = false,
    //                 onClick = { showUnderConstruction = true }
    //             )
    //
    //             // TODO: Other Items
    //         }
    //     }
    // ) {

    Scaffold(
        modifier = modifier.fillMaxSize(),

        // topBar = {
        //     TopAppBar(
        //         title = { Text(text = "Top Bar") },
        //         actions = {
        //             // RowScope here, so these icons will be placed horizontally
        //             IconButton(onClick = {
        //
        //
        //             }) {
        //                 Icon(Icons.Filled.Search, contentDescription = null)
        //             }
        //         },
        //         navigationIcon = {
        //             IconButton(onClick = {
        //                 scope.launch {
        //                     drawerState.apply {
        //                         if (isClosed) open() else close()
        //                     }
        //                 }
        //             }) {
        //                 Icon(
        //                     Icons.Filled.Menu,
        //                     contentDescription = "Меню"
        //                 )
        //             }
        //         },
        //     )
        //
        // },


        bottomBar = {
            //TODO:
            NavigationBar {
                MainSection
                    .values()
                    .forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.image, contentDescription = null) },
                            label = { Text(stringResource(item.titleRes)) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                component.onOutput(MainComponent.Output.OnOpenSection(item))
                            }
                        )
                    }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        // , floatingActionButton = {
        //     ExtendedFloatingActionButton(
        //         onClick = {
        //             scope.launch {
        //                 snackbarHostState.showSnackbar(
        //                     "Test Snackbar",
        //                     actionLabel = "Действие",
        //                     withDismissAction = true
        //                 )
        //             }
        //         }
        //     ) {
        //         Icon(
        //             Icons.Rounded.Adb,
        //             contentDescription = "Contact profile picture"
        //         )
        //     }
        // },
        // floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier = modifier.padding(paddingValues),
            animation = stackAnimation(fade() + scale())
        ) {
            when (val child = it.instance) {
                is MainComponent.Child.Details -> DetailsScreen(component = child.component)
                is MainComponent.Child.List -> ListScreen(component = child.component)
                is MainComponent.Child.MoviesSearch -> SearchMoviesScreen(component = child.component)
                is MainComponent.Child.MoviesList -> MoviesListScreen(component = child.component)
            }
        }
    }


    if (showUnderConstruction) {
        DialogUnderConstruction {
            showUnderConstruction = false
        }
    }
}


@Composable
fun ListWithBug(myList: List<String>) {
    var items = 0

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
                items++ // Avoid! Side-effect of the column recomposing.
            }
        }
        Text("Count: $items")
    }
}