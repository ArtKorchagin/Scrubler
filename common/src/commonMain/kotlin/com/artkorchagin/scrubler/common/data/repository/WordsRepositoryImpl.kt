package com.artkorchagin.scrubler.common.data.repository

import com.artkorchagin.scrubler.common.BuildConfig

class WordsRepositoryImpl : WordsRepository {
    override fun getTestString() = "It's working! API=${BuildConfig.API_KEY}"
}