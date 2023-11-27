package com.example.uf1_proyecto_compose.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1
)
abstract class ToDoAppDB: RoomDatabase() {

    abstract val dao: TaskDAO
}