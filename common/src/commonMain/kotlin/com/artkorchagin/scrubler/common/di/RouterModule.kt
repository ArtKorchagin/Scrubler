package com.artkorchagin.scrubler.common.di

import com.arkivanov.decompose.ComponentContext
import com.artkorchagin.scrubler.common.presentation.router.ComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.main.component.MainComponent
import com.artkorchagin.scrubler.common.presentation.ui.main.router.MainComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.main.router.MainConfig
import com.artkorchagin.scrubler.common.presentation.ui.root.component.RootComponent
import com.artkorchagin.scrubler.common.presentation.ui.root.router.RootComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.root.router.RootConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val routerModule = module {
    factory<ComponentRouter<MainConfig, MainComponent.Child>>(
        named(MainComponentRouter.NAME)
    ) { (componentContext: ComponentContext) -> MainComponentRouter(componentContext) }


    factory<ComponentRouter<RootConfig, RootComponent.Child>>(
        named(RootComponentRouter.NAME)
    ) { (componentContext: ComponentContext) -> RootComponentRouter(componentContext) }
}