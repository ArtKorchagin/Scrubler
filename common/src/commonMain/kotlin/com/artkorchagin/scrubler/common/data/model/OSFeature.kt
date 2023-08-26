package com.artkorchagin.scrubler.common.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OSFeature(
    val id: String,
    val type: String, //Make enum,
    val attributes: OSAttributes
)