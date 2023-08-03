package com.artkorchagin.scrubler.common.core.coroutines

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

fun CoroutineScope(coroutineContext: CoroutineContext, lifecycle: Lifecycle): CoroutineScope {
    val scope = CoroutineScope(coroutineContext)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}

fun LifecycleOwner.coroutineScope(coroutineContext: CoroutineContext) = CoroutineScope(coroutineContext, lifecycle)