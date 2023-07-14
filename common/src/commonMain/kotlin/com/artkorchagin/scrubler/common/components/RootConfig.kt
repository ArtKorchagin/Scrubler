package com.artkorchagin.scrubler.common.components

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


internal sealed interface RootConfig : Parcelable {
    @Parcelize
    object List : RootConfig
    @Parcelize
    data class Details(val item: String) : RootConfig
}