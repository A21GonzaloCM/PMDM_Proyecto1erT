package com.example.uf1_proyecto_compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface TaskDAO {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertTask(task:Task)

    @Delete
    suspend fun deleteTask(task:Task)

    @Query("SELECT * FROM task where id = :id")
    suspend fun getTaskById(id: Int) : Task?

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>
}