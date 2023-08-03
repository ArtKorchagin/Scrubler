package com.artkorchagin.scrubler.common.presentation.dialogs


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


//TODO: Не работает
@Composable
fun DialogUnderConstruction(
    onDismiss: (() -> Unit),
) {

    PlatformDialog(
        title = {
            Text("Раздел ещё не готов")
        },
        text = {
            Text("Мы прикладываем все усилия, чтобы завершить данный раздел как можно скорее! Спасибо за терпение. ")
        },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Оки :(")
            }
        },
        onDismiss = onDismiss
    )
}

@Composable
expect fun PlatformDialog(
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit),
    onDismiss: (() -> Unit)? = null,
    dismissButton: @Composable (() -> Unit)? = null
)

