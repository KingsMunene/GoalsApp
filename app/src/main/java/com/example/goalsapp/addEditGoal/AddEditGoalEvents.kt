package com.example.goalsapp.addEditGoal

sealed class AddEditGoalEvents{
    data class OnEditTitle(val title: String): AddEditGoalEvents()

    data class OnEditDescription(val description: String): AddEditGoalEvents()

    object SaveGoal: AddEditGoalEvents()
}
