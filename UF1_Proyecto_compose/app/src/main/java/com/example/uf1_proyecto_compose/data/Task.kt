package com.example.uf1_proyecto_compose.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @ColumnInfo(name ="title")
    val title: String,
    @ColumnInfo(name ="description")
    val description: String?,
    @ColumnInfo(name ="is_done")
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int=0
)
