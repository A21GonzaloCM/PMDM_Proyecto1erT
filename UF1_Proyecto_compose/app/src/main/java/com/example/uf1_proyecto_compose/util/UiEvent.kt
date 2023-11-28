package com.example.uf1_proyecto_compose.util

sealed class UiEvent{

    object PopBackStak: UiEvent()
    data class Navigate(val route: String): UiEvent()

    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()

}
