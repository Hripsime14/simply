package com.example.simplytechnologiesproject.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.simplytechnologiesproject.ActionListItem
import com.example.simplytechnologiesproject.AlarmDialogState
import com.example.simplytechnologiesproject.R
import com.example.simplytechnologiesproject.actiontype.ActionType
import com.example.simplytechnologiesproject.extension.orDefault
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var actionItemList = listOf(
        ActionListItem(id = 0, stringId = R.string.lock, drawableId = R.drawable.lock, selected = true, actionType = ActionType.Lock),
        ActionListItem(id = 1, stringId = R.string.unlock, drawableId = R.drawable.unlock, actionType = ActionType.Unlock),
        ActionListItem(id = 2, stringId = R.string.climate, drawableId =  R.drawable.climate),
        ActionListItem(id = 3, stringId = R.string.charge, drawableId = R.drawable.charge),
        ActionListItem(id = 4, stringId = R.string.lights, drawableId = R.drawable.lights)
    )
    private val _alertDialogFlow = MutableStateFlow<AlarmDialogState>(AlarmDialogState.Hide)
    val alertDialogFlow: StateFlow<AlarmDialogState> = _alertDialogFlow

    val actionItemListFlow = savedStateHandle.getStateFlow(LIST_KEY, actionItemList)

    fun changeSelection(actionType: ActionType) {
       actionItemList = actionItemList.map { item ->
           item.actionType?.let {
               item.copy(selected = it == actionType)
           }.orDefault(item)
       }
        savedStateHandle[LIST_KEY] = actionItemList
    }

    fun changeAlertDialogState(alarmDialogState: AlarmDialogState) {
       _alertDialogFlow.value = alarmDialogState
    }

    private companion object {
        const val LIST_KEY = "list_key"
        const val SHOW_ALERT_DIALOG_KEY = "show_alert_dialog_click"
    }
}