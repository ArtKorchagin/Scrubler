package com.artkorchagin.scrubler.common.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.artkorchagin.scrubler.common.presentation.ui.list.store.ListStore
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storeModule = module {

    /* TODO: named Enum or another way? */

    singleOf<StoreFactory>(::DefaultStoreFactory)

    factoryOf(::ListStore)

    // factoryOf(named("ListStore"))(::ListStore)
}