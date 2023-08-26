package com.artkorchagin.scrubler.common.data.repository

import com.artkorchagin.scrubler.common.BuildConfig
import com.artkorchagin.scrubler.common.core.coroutines.ScrublerDispatchers
import com.artkorchagin.scrubler.common.data.model.Movie
import com.artkorchagin.scrubler.common.data.model.TestItem
import com.artkorchagin.scrubler.common.data.network.OpensubtitlesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class WordsRepositoryImpl(
    private val scrublerDispatchers: ScrublerDispatchers,
    private val opensubtitlesApi: OpensubtitlesApi,
) : WordsRepository {

    private val tempList = List(100) { "(Item $it ${getTestString()}" }.toMutableList()

    override fun getTestString() = "Test String"

    override suspend fun getItems(): List<String> = withContext(scrublerDispatchers.io) {
        delay(3000)
        tempList
    }

    override suspend fun removeItem(item: String) {
        withContext(scrublerDispatchers.io) {
            delay(3000)
            tempList.remove(item)
        }
    }

    override suspend fun getItemInfo(item: String) = withContext(scrublerDispatchers.io) {
        delay(1500)
        TestItem(
            item,
            "DetailedInfo: $item"
        )
    }

    override suspend fun getFeatures(query: String) = withContext(scrublerDispatchers.io) {
        opensubtitlesApi.getFeatures(query).data.orEmpty()
    }

    override suspend fun getMovies() = withContext(scrublerDispatchers.io) {
        delay(500)
        listOf(
            Movie("Крёстный отец"),
            Movie("Форест гамп"),
            Movie("Зелёная миля"),
        )
    }
}
