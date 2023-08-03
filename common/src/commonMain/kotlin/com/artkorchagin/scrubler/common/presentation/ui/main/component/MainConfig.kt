package com.artkorchagin.scrubler.common.presentation.ui.main.component

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


internal sealed interface MainConfig : Parcelable {
    @Parcelize
    object List : MainConfig
    @Parcelize
    data class Details(val item: String) : MainConfig
}