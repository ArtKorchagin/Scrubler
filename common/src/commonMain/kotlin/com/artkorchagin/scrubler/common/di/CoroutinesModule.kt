package com.artkorchagin.scrubler.common.di

import com.artkorchagin.scrubler.common.core.coroutines.scrublerDispatchers
import org.koin.dsl.module

val coroutinesModule = module {
    single { scrublerDispatchers }
}
