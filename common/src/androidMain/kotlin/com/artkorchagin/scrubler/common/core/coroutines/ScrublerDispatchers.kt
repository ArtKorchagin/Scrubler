package com.artkorchagin.scrubler.common.core.coroutines

import kotlinx.coroutines.Dispatchers

actual val scrublerDispatchers = ScrublerDispatchers(
    io = Dispatchers.IO,
    main = Dispatchers.Main,
    default = Dispatchers.Default,
    unconfined = Dispatchers.Unconfined,
)