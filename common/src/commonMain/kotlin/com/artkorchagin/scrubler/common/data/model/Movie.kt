package com.artkorchagin.scrubler.common.data.model

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(
    val title: String
) : Parcelable