package com.example.uf1_proyecto_compose.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title: String,
    val description: String?,
    val done: Boolean,
    @PrimaryKey val id: Int? = null
)
