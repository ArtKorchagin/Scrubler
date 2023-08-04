package com.artkorchagin.scrubler.common.data.repository

import com.artkorchagin.scrubler.common.BuildConfig
import com.artkorchagin.scrubler.common.core.coroutines.ScrublerDispatchers
import com.artkorchagin.scrubler.common.data.model.TestItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class WordsRepositoryImpl(
    private val scrublerDispatchers: ScrublerDispatchers
) : WordsRepository {

    private val tempList = List(100) { "(Item $it ${getTestString()}" }.toMutableList()

    override fun getTestString() = "It's working! API=${BuildConfig.API_KEY}"

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
}
