package com.example.simplytechnologiesproject

sealed class LoadingState {
    object Loading: LoadingState()
    object NotLoading: LoadingState()
    object Idle: LoadingState()
}