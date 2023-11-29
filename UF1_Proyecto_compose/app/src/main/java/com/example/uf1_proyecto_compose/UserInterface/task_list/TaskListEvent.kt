package com.example.uf1_proyecto_compose.UserInterface.task_list

import com.example.uf1_proyecto_compose.data.Task

sealed class TaskListEvent {

    data class OnDeleteTaskClick(val task: Task) : TaskListEvent()
    data class OnDoneChange(val task: Task, val isDone: Boolean) : TaskListEvent()
    object OnUndoDeleteClick : TaskListEvent()

    data class OnTaskClick(val task: Task) : TaskListEvent()

    object OnAddTaskClick : TaskListEvent()
}
