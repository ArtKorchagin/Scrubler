package com.artkorchagin.scrubler.common.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

data class ScrublerDispatchers(
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val unconfined: CoroutineDispatcher
)

expect val scrublerDispatchers: ScrublerDispatchers