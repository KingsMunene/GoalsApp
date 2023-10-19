package com.example.goalsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


// The instance of the RoomDatabase that has the instance of the GoalDao
@Database(
    entities = [Goal::class],
    version = 1
)
abstract class GoalDatabase: RoomDatabase() {

    abstract val dao : GoalDao
}