package com.artkorchagin.scrubler.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OSAttributes(
    val title: String,
    @SerialName("original_title")
    val originalTitle: String?,
    val year: String,
    @SerialName("subtitles_count")
    val subtitlesCount: Int,
    @SerialName("seasons_count")
    val seasonsCount: Int,
    @SerialName("parent_title")
    val parentTitle: String?,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("episode_number")
    val episodeNumber: Int?,
    @SerialName("feature_id")
    val featureId: Int,
    @SerialName("feature_type")
    val featureType: OSFeatureType,
    val url: String,
    @SerialName("img_url")
    val imgUrl: String
)