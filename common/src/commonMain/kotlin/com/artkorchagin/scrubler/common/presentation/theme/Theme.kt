package com.artkorchagin.scrubler.common.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.artkorchagin.scrubler.common.presentation.extensions.toColor
import com.artkorchagin.scrubler.common.resources.MR

private val darkColorScheme = darkColorScheme()
private val lightColorScheme = lightColorScheme()

@Composable
internal fun ScrublerTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = colorScheme().copy(
            primary = MR.colors.primary.toColor(),

            // secondary = MR.colors.secondary.toColor(),
            // tertiary = MR.colors.tertiary.toColor(),

           // background = MR.colors.primary.toColor(),

            surface = MR.colors.surface.toColor(),
            // surfaceVariant = MR.colors.surfaceVariant.toColor(),
            // onPrimary = MR.colors.onPrimary.toColor(),
            // onSecondary = MR.colors.onSecondary.toColor(),
            // onBackground = MR.colors.onBackground.toColor(),
            // onSurface = MR.colors.onSurface.toColor(),
            // onSurfaceVariant = MR.colors.onSurfaceVariant.toColor(),
            // outline = MR.colors.outline.toColor()
        ),
        typography = replyTypography,
        shapes = replyShapes,
        content = content
    )
}

//TODO: Check Dynamic Colors
@Composable
private fun colorScheme(darkTheme: Boolean = isSystemInDarkTheme()) =
    if (darkTheme) darkColorScheme else lightColorScheme