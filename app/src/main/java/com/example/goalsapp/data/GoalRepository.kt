package com.example.goalsapp.data

import kotlinx.coroutines.flow.Flow

// Repository to access the data sources
// It decides which data to forward to the viewmodel
interface GoalRepository {
    suspend fun insertGoal(goal: Goal)

    suspend fun deleteGoal(goal: Goal)

    suspend fun getGoalById(id: Int): Goal?

    fun getGoals(): Flow<List<Goal>>
}