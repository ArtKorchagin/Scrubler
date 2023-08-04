package com.artkorchagin.scrubler.common.di

import com.artkorchagin.scrubler.common.data.repository.WordsRepository
import com.artkorchagin.scrubler.common.data.repository.WordsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<WordsRepository> { WordsRepositoryImpl(get()) }
}