package com.artkorchagin.scrubler.common.presentation.ui.root.router

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


sealed interface RootConfig : Parcelable {
    @Parcelize
    object Main : RootConfig
}