package com.example.uf1_proyecto_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TasksAppDB: RoomDatabase() {

    abstract val dao: TaskDAO
}