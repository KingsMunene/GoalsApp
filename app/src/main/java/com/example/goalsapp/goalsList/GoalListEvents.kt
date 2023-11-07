package com.example.goalsapp.goalsList

import com.example.goalsapp.data.Goal

sealed class GoalListEvents{
    data class GoalClicked(val goal: Goal): GoalListEvents()

    data class ClickGoalDone(val goal: Goal, val isDone: Boolean): GoalListEvents()

    data class DeleteGoal(val goal: Goal): GoalListEvents()

    object AddNewGoal : GoalListEvents()

    object UndoDeleteGoal : GoalListEvents()

}
