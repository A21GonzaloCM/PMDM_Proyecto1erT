package com.example.uf1_proyecto_compose.UserInterface.task_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.rememberScaffoldState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uf1_proyecto_compose.util.UiEvent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val tasks = viewModel.tasks.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TaskListEvent.OnUndoDeleteClick)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text("Tasks App") },backgroundColor = MaterialTheme.colors.primary)  },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(TaskListEvent.OnAddTaskClick) }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()
        ){
            items(tasks.value){ task->
                TaskItem(task = task,
                    onEvent = viewModel::onEvent,
                    modifier= Modifier.fillMaxWidth().clickable {
                        viewModel.onEvent(TaskListEvent.OnTaskClick(task))
                    }
                        .padding(15.dp
                        )
                )
            }
        }
    }
}