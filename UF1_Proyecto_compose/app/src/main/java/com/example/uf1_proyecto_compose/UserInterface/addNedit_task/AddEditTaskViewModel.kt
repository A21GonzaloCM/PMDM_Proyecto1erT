package com.example.uf1_proyecto_compose.UserInterface.addNedit_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.data.Task
import com.example.uf1_proyecto_compose.data.TaskRepository
import com.example.uf1_proyecto_compose.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var task by mutableStateOf<Task?>(null)
        private set

    var title = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val taskId = savedStateHandle.get<Int>("taskId")
        if(taskId != -1){
            viewModelScope.launch {
                if (taskId != null) {
                    repository.getTaskById(taskId)?.let{ task ->
                        title = task.title as MutableState<String>
                        description= (task.description ?: "") as MutableState<String>
                        this@AddEditTaskViewModel.task = task
                    }
                }

            }
        }

    }
    fun onEvent(event: AddEditTaskEvent){
        when(event){
            is AddEditTaskEvent.OnTitleChange->{
                title = event.title as MutableState<String>
            }
            is AddEditTaskEvent.OnDescriptionChange->{
                description= event.description as MutableState<String>
            }
            is AddEditTaskEvent.OnSaveTaskClick->{
                viewModelScope.launch {
                    if(title.toString().isBlank()){
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTask(
                        Task(
                            title= title.toString(),
                            description= description.toString(),
                            isDone = task?.isDone ?: false
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)

        }
    }
}