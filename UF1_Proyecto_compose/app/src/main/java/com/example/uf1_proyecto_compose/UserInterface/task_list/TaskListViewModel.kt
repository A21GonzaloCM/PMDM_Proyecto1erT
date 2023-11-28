package com.example.uf1_proyecto_compose.UserInterface.task_list

import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.data.TaskRepository
import com.example.uf1_proyecto_compose.util.UiEvent
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltAndroidApp
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    val tasks = repository.getTasks()

    private val _uiEvent= Channel<UiEvent>()
    val uiEvent= _uiEvent.receiveAsFlow()

}