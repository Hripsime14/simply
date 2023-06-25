package com.example.simplytechnologiesproject.snackbar

import androidx.compose.material3.SnackbarDuration

sealed class AppSnackbarState {
    data class Show(
        val text: String = "",
        val duration: SnackbarDuration = SnackbarDuration.Short
    ) : AppSnackbarState()

    object Hide : AppSnackbarState()
}
