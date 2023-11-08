package com.example.goalsapp.goalsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalsapp.data.Goal
import com.example.goalsapp.data.GoalRepository
import com.example.goalsapp.utils.Routes
import com.example.goalsapp.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsListViewModel @Inject constructor(
    private val repository: GoalRepository
): ViewModel() {

    val goalsList = repository.getGoals()

    private val _uiEvents = Channel<UiEvents>()

    val uiEvents = _uiEvents.receiveAsFlow()

    private var deletedGoal: Goal? = null

    fun onEvent(event: GoalListEvents){
        when(event){
            GoalListEvents.AddNewGoal -> {
                sendEvent(UiEvents.Navigate(Routes.AddEditGoalItem.name))

            }
            is GoalListEvents.ClickGoalDone -> {
                viewModelScope.launch{
                    repository.insertGoal(event.goal.copy(isDone = event.isDone))
                }
            }
            is GoalListEvents.DeleteGoal -> {
                viewModelScope.launch {
                    deletedGoal = event.goal
                    repository.deleteGoal(event.goal)
                    sendEvent(UiEvents.ShowSnackBar(
                        message = "Goal has been deleted Successfully!!",
                        actionLabel = "Undo"
                    ))
                }

            }
            is GoalListEvents.GoalClicked -> {
                sendEvent(UiEvents.Navigate(Routes.AddEditGoalItem.name + "?goalId=${event.goal.id}"))
            }
            GoalListEvents.UndoDeleteGoal -> {
               deletedGoal?.let {
                   viewModelScope.launch {
                       repository.insertGoal(it)
                   }
               }
            }
        }
    }

    fun sendEvent(event: UiEvents){
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }

}