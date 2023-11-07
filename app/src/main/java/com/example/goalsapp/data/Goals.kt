package com.example.goalsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Goal(
    val title: String,
    val description: String,
    val isDone: Boolean,
    @PrimaryKey val id: Int?
)
