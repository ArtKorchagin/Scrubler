package com.artkorchagin.scrubler.common.data.repository

import com.artkorchagin.scrubler.common.data.model.OSFeature
import com.artkorchagin.scrubler.common.data.model.Movie
import com.artkorchagin.scrubler.common.data.model.TestItem

interface WordsRepository {

    fun getTestString(): String
    suspend fun getItems(): List<String>

    suspend fun removeItem(item: String)

    suspend fun getItemInfo(item: String): TestItem
    suspend fun getFeatures(query: String): List<OSFeature>
    suspend fun getMovies(): List<Movie>
}