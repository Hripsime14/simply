package com.example.simplytechnologiesproject

import com.example.simplytechnologiesproject.actiontype.ActionType


data class ActionListItem(
    val id: Int,
    val stringId: Int,
    val drawableId: Int,
    val selected: Boolean = false,
    val actionType: ActionType? = null
)
