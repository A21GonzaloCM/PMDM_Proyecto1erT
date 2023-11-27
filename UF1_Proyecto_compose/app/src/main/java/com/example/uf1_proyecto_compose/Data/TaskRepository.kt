package com.example.uf1_proyecto_compose.Data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insertTask(task:Task)

    suspend fun deleteTask(task:Task)

    suspend fun getTaskById(id: Int) : Task?

    fun getTasks(): Flow<List<Task>>
}