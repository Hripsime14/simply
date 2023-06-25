package com.example.simplytechnologiesproject.actiontype

sealed class ActionType {
    object Lock : ActionType()
    object Unlock: ActionType()
}
