package com.example.simplytechnologiesproject

sealed class AlarmDialogState {
    object Hide: AlarmDialogState()
    object Show: AlarmDialogState()
}
