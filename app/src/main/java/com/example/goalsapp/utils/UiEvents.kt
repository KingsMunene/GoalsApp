package com.example.goalsapp.utils

sealed class UiEvents{
    data class ShowSnackBar(
        val message: String,
        val actionLabel: String
    ): UiEvents()

    data class Navigate(val route: String): UiEvents()

    object PopBackStack: UiEvents()
}
