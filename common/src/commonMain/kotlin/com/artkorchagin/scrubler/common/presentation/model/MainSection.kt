package com.artkorchagin.scrubler.common.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.MovieCreation
import androidx.compose.material.icons.rounded.MusicVideo
import androidx.compose.material.icons.rounded.YoutubeSearchedFor
import androidx.compose.ui.graphics.vector.ImageVector
import com.artkorchagin.scrubler.common.resources.MR
import dev.icerock.moko.resources.StringResource

enum class MainSection(
    val titleRes: StringResource,
    val image: ImageVector
) {
    Words(MR.strings.words, Icons.Rounded.EditNote),
    Movies(MR.strings.movies, Icons.Rounded.MovieCreation),
    Music(MR.strings.music, Icons.Rounded.MusicVideo),
    Youtube(MR.strings.youtube, Icons.Rounded.YoutubeSearchedFor),
    Settings(MR.strings.settings, Icons.Rounded.EditNote)
}