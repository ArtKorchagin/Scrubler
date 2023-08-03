package com.artkorchagin.scrubler.common.presentation.ui.root.component

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


internal sealed interface RootConfig : Parcelable {
    @Parcelize
    object Main : RootConfig
}