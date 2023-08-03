package com.artkorchagin.scrubler.common.presentation.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable

@Composable
actual fun PlatformDialog(
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    confirmButton: @Composable (() -> Unit),
    onDismiss: (() -> Unit)?,
    dismissButton: @Composable (() -> Unit)?
) {
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
            onDismiss?.invoke()
        },
        title = title,
        text = text,
        confirmButton = confirmButton,
        dismissButton = dismissButton
    )
}