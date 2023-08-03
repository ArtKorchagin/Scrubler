package com.artkorchagin.scrubler.common.presentation.ui.main.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.rounded.Adb
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DefaultDetailsComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.DefaultListComponent
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DetailsComponent
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DetailsContent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListContent
import com.artkorchagin.scrubler.common.presentation.ui.root.component.items
import com.artkorchagin.scrubler.common.presentation.dialogs.DialogUnderConstruction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class DefaultMainComponent(
    componentContext: ComponentContext
) : MainComponent,
    KoinComponent,
    ComponentContext by componentContext {

    //TODO CoroutinesModule
    private val mainContext = SupervisorJob() + Dispatchers.Main
    private val navigation = StackNavigation<MainConfig>()

    //TODO param
    private val listInput = MutableSharedFlow<ListComponent.Input>(extraBufferCapacity = Int.MAX_VALUE)

    override val stack: Value<ChildStack<*, MainComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = MainConfig.List,
            handleBackButton = true,
            childFactory = ::createChild //TODO: Maybe move to the Koin?
        )


    // TODO: ChildFactory
    // TODO: MainConfigToChildMapper (???)
    private fun createChild(config: MainConfig, componentContext: ComponentContext): MainComponent.Child {

        // return get { parametersOf(config, componentContext) }


        return when (config) {
            MainConfig.List -> MainComponent.Child.ListChild(listComponent(componentContext))
            is MainConfig.Details -> MainComponent.Child.DetailsChild(detailsComponent(componentContext, config.item))
        }
    }

    private fun detailsComponent(componentContext: ComponentContext, item: String): DetailsComponent {
        return DefaultDetailsComponent(
            componentContext,
            item,
            mainContext,

            onItemDelete = { itemName ->
                navigation.pop {
                    println("xxx-> instance=${stack.value.active.instance}")
                    listInput.tryEmit(ListComponent.Input.ItemDeleted(itemName))
                }
            }
        )
    }

    private fun listComponent(componentContext: ComponentContext): ListComponent {
        return DefaultListComponent(
            componentContext,
            listInput,
            mainContext
        ) { item -> navigation.push(MainConfig.Details(item)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(component: MainComponent, modifier: Modifier = Modifier) {

    var showUnderConstruction by remember { mutableStateOf(false) }
    var snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selectedItem by remember { mutableStateOf(0) }


    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape
            ) {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    shape = RectangleShape,
                    icon = { Icon(Icons.Filled.Movie, contentDescription = null) },
                    label = { Text("Учить слова по фильмам") },
                    selected = false,
                    onClick = { showUnderConstruction = true }
                )

                // TODO: Other Items
            }
        }
    ) {

        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Top Bar") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Меню"
                            )
                        }
                    },
                )

            },
            bottomBar = {
                //TODO:
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.image, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                // TODO Navigate!
                            }
                        )
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Test Snackbar",
                                actionLabel = "Действие",
                                withDismissAction = true
                            )
                        }
                    }
                ) {
                    Icon(
                        Icons.Rounded.Adb,
                        contentDescription = "Contact profile picture"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
        ) { paddingValues ->
            Children(
                stack = component.stack,
                modifier = modifier.padding(paddingValues),
                animation = stackAnimation(fade() + scale())
            ) {
                when (val child = it.instance) {
                    is MainComponent.Child.DetailsChild -> DetailsContent(component = child.component)
                    is MainComponent.Child.ListChild -> ListContent(component = child.component)
                }
            }

        }
    }

    if (showUnderConstruction) {
        DialogUnderConstruction {
            showUnderConstruction = false
        }
    }
}