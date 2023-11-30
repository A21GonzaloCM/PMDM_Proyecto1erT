package com.example.uf1_proyecto_compose.UserInterface.addNedit_task

sealed class AddEditTaskEvent{
    data class OnTitleChange(val title: String): AddEditTaskEvent()
    data class OnDescriptionChange(val description: String): AddEditTaskEvent()

    object OnSaveTaskClick: AddEditTaskEvent()
}
