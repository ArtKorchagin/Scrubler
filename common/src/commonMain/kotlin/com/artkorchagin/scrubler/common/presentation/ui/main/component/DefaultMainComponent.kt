package com.artkorchagin.scrubler.common.presentation.ui.main.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.artkorchagin.scrubler.common.presentation.router.ComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.main.router.MainComponentRouter
import com.artkorchagin.scrubler.common.presentation.ui.main.router.MainConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

internal class DefaultMainComponent(
    componentContext: ComponentContext,
    private val output: (MainComponent.Output) -> Unit,
) : MainComponent,
    KoinComponent,
    ComponentContext by componentContext {

    private val router = get<ComponentRouter<MainConfig, MainComponent.Child>>(
        named(MainComponentRouter.NAME)
    ) { parametersOf(componentContext) }

    override val stack: Value<ChildStack<*, MainComponent.Child>> = router.stack

    override fun onOutput(output: MainComponent.Output) = output(output)
}