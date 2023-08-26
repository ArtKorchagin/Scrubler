package com.artkorchagin.scrubler.common.presentation.ui.main.router

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


sealed interface MainConfig : Parcelable {
    @Parcelize
    object List : MainConfig

    @Parcelize
    data class Details(val item: String) : MainConfig

    @Parcelize
    object MoviesSearch : MainConfig

    @Parcelize
    object MoviesList : MainConfig
}