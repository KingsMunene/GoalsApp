package com.example.goalsapp.data

import kotlinx.coroutines.flow.Flow

class GoalRepositoryImplementation(
    private val goalDao: GoalDao
): GoalRepository{
    override suspend fun insertGoal(goal: Goal) {
        goalDao.insertGoal(goal)
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal)
    }

    override suspend fun getGoalById(id: Int): Goal? {
        return goalDao.getGoalById(id)

    }

    override fun getGoals(): Flow<List<Goal>> {
       return goalDao.getGoals()
    }

}