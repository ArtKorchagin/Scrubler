package com.artkorchagin.scrubler.common.di

import networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            coroutinesModule,
            networkModule,
            repositoryModule,
            storeModule,
            routerModule
        )
    }
}