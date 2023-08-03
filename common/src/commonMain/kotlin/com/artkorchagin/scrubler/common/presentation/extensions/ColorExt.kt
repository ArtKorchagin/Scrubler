package com.artkorchagin.scrubler.common.presentation.extensions

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun ColorResource.toColor() = colorResource(this)