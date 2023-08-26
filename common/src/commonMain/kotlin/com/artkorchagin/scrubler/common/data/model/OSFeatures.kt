package com.artkorchagin.scrubler.common.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OSFeatures(
    val data: List<OSFeature>?
) //TODO: Make universal response