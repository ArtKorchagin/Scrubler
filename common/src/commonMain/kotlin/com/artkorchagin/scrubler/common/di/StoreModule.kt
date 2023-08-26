package com.artkorchagin.scrubler.common.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.artkorchagin.scrubler.common.presentation.ui.details.store.DetailsStore
import com.artkorchagin.scrubler.common.presentation.ui.list.component.ListComponent
import com.artkorchagin.scrubler.common.presentation.ui.list.store.ListStore
import com.artkorchagin.scrubler.common.presentation.ui.movies.list.store.MoviesListStore
import com.artkorchagin.scrubler.common.presentation.ui.movies.search.store.MoviesSearchStore
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storeModule = module {

    singleOf<StoreFactory>(::DefaultStoreFactory)

    factory { MoviesSearchStore(get(), get()) }

    factory { MoviesListStore(get(), get()) }

    factory { (input: Flow<ListComponent.Input>) -> ListStore(get(), get(), input) }

    factory { (item: String) -> DetailsStore(get(), get(), item) }
}