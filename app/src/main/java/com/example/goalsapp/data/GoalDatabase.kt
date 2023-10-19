package com.example.goalsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


// The instance of the RoomDatabase that has the instance of the GoalDao
// We define the entities of the database in an Array
@Database(
    entities = [Goal::class],
    version = 1
)
// The database is an abstract class that extends the RoomDatabase
// An abstract class cannot be instantiated
abstract class GoalDatabase: RoomDatabase() {

    abstract val dao : GoalDao
}