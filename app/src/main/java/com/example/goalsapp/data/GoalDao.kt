package com.example.goalsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// This is the Data Access Object (Dao)
// It allows us to implement functions to manipulate data in our Entity Goal
@Dao
interface GoalDao {

    // Here we implement a suspend function to insert a new goal into the entity
    // We implement a strategy to handle conflict where the new goal might have the same id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Query("SELECT * FROM goal WHERE id = :id")
    suspend fun getGoalById(id: Int): Goal?

    @Query("SELECT * FROM goal")
    fun getGoals(): Flow<List<Goal>>
}