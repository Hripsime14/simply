package com.example.simplytechnologiesproject.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplytechnologiesproject.ActionListItem
import com.example.simplytechnologiesproject.LoadingState
import com.example.simplytechnologiesproject.R
import com.example.simplytechnologiesproject.actiontype.ActionType
import com.example.simplytechnologiesproject.dialogue.AlarmDialogState
import com.example.simplytechnologiesproject.extension.orDefault
import com.example.simplytechnologiesproject.snackbar.AppSnackbarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var actionItemList = listOf(
        ActionListItem(
            id = 0,
            stringId = R.string.lock,
            drawableId = R.drawable.lock,
            selected = true,
            actionType = ActionType.LOCK
        ),
        ActionListItem(
            id = 1,
            stringId = R.string.unlock,
            drawableId = R.drawable.unlock,
            actionType = ActionType.UNLOCK
        ),
        ActionListItem(id = 2, stringId = R.string.climate, drawableId = R.drawable.climate),
        ActionListItem(id = 3, stringId = R.string.charge, drawableId = R.drawable.charge),
        ActionListItem(id = 4, stringId = R.string.lights, drawableId = R.drawable.lights)
    )
    private val _alertDialogFlow = MutableStateFlow<AlarmDialogState>(AlarmDialogState.Hide)
    val alertDialogFlow: StateFlow<AlarmDialogState> = _alertDialogFlow

    private val _snackBarFlow = MutableSharedFlow<AppSnackbarState>()
    val snackBarFlow = _snackBarFlow.asSharedFlow()

    val actionItemListFlow = savedStateHandle.getStateFlow(LIST_KEY, actionItemList)

    private val _isLoading = MutableStateFlow<LoadingState>(LoadingState.Idle)
    val isLoading: StateFlow<LoadingState> = _isLoading

    fun showAndHideProgressBarWithDelay(millis: Long) {
        viewModelScope.launch {
            _isLoading.value = LoadingState.Loading
            delay(millis)
            _isLoading.value = LoadingState.NotLoading
            showOrHideSnackbar(AppSnackbarState.Show("Ariya unlocked"))
        }
    }

    private fun setProgressBarDefaultState() {
        _isLoading.value = LoadingState.Idle
    }

    fun changeSelection(actionType: ActionType) {
        actionItemList = actionItemList.map { item ->
            item.actionType?.let {
                item.copy(selected = it == actionType)
            }.orDefault(item)
        }
        savedStateHandle[LIST_KEY] = actionItemList
        setProgressBarDefaultState()
    }

    fun changeAlertDialogState(alarmDialogState: AlarmDialogState) {
        _alertDialogFlow.value = alarmDialogState
    }

    fun showOrHideSnackbar(appSnackbarState: AppSnackbarState) {
        viewModelScope.launch {
            _snackBarFlow.emit(appSnackbarState)
        }
    }

    private companion object {
        const val LIST_KEY = "list_key"
    }
}