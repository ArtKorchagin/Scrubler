package com.artkorchagin.scrubler.common.data.network

import com.artkorchagin.scrubler.common.BuildConfig
import com.artkorchagin.scrubler.common.data.model.OSFeatures
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


private const val BASE_OPENSUBTITLES_URL = "https://api.opensubtitles.com/api/v1/"

class OpensubtitlesApi(val client: HttpClient) {

    suspend fun getFeatures(query: String): OSFeatures =
        client.get(BASE_OPENSUBTITLES_URL + "features") {
            url {
                headers.append("Api-Key", BuildConfig.OPENSUBTITLES_API_KEY)
                parameters.append("query", query)
            }

        }
            .body() //TODO: Handle errors


    // httpClient.get(NetworkConstants.Pokemon.route) {
    //     url {
    //         parameters.append("limit", PageSize.toString())
    //         parameters.append("offset", (page * PageSize).toString())
    //     }
    //     contentType(ContentType.Application.Json)
    // }
}


// suspend fun fetchPeople() = client.get("$baseUrl/astros.json").body<AstroResult>()