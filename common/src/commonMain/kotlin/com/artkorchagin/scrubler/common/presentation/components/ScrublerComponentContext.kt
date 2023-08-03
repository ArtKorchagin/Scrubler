package com.artkorchagin.scrubler.common.presentation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.Lifecycle

interface ScrublerComponentContext : ComponentContext {
    // TODO: Custom things
}

class DefaultScrublerComponentContext(
    componentContext: ComponentContext
) : ScrublerComponentContext, ComponentContext by componentContext {
    // TODO: Custom things
}


fun ScrublerComponentContext.childScrublerContext(key: String, lifecycle: Lifecycle? = null): ScrublerComponentContext =
    DefaultScrublerComponentContext(
        componentContext = childContext(key = key, lifecycle = lifecycle)
        // TODO: Supply additional dependencies here
    )