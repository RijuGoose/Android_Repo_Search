package com.example.reposearch.ui.common


sealed class ScreenState<out T : Any> {
    object Idle : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
    data class Success<out T : Any>(val value: T) : ScreenState<T>()
    data class Error(val message: String) : ScreenState<Nothing>()
}