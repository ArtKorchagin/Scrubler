package com.artkorchagin.scrubler.common.presentation.dialogs

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformDialog(
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    confirmButton: @Composable (() -> Unit),
    onDismiss: (() -> Unit)?,
    dismissButton: @Composable (() -> Unit)?
) {
    TODO()
}