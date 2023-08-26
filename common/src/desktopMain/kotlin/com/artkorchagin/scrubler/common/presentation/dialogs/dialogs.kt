package com.artkorchagin.scrubler.common.presentation.dialogs

import androidx.compose.foundation.onClick
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
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