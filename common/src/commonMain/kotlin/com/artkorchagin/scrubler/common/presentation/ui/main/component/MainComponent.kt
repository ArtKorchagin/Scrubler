package com.artkorchagin.scrubler.common.presentation.ui.main.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.artkorchagin.scrubler.common.presentation.model.MainSection
import com.artkorchagin.scrubler.common.presentation.ui.details.component.DetailsComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.component.MoviesListComponent
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.component.MoviesSearchComponent

interface MainComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onOutput(output: Output)

    sealed class Child {
        class List(val component: ListComponent) : Child()
        class Details(val component: DetailsComponent) : Child()
        class MoviesSearch(val component: MoviesSearchComponent) : Child()
        class MoviesList(val component: MoviesListComponent) : Child()
    }

    sealed interface Output {
        class OnOpenSection(val item: MainSection) : Output
        object OnBack : Output
    }
}