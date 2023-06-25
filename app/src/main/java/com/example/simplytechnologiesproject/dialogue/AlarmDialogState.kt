package com.example.simplytechnologiesproject.dialogue

sealed class AlarmDialogState {
    object Hide: AlarmDialogState()
    object Show: AlarmDialogState()
}
