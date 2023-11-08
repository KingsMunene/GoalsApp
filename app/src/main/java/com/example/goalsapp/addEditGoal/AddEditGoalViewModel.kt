package com.example.goalsapp.addEditGoal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalsapp.data.Goal
import com.example.goalsapp.data.GoalRepository
import com.example.goalsapp.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGoalViewModel @Inject constructor(
    private val repository: GoalRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var goal by mutableStateOf<Goal?>(null)
        private set
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    val _uiEvents = Channel<UiEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        val goalId = savedStateHandle.get<Int>("goalId")!!

        if (goalId != -1) {
            viewModelScope.launch {
                repository.getGoalById(goalId)?.let { goal ->
                    title = goal.title
                    description = goal.description
                    this@AddEditGoalViewModel.goal = goal
                }
            }
        }
    }

    fun onEvent(event: AddEditGoalEvents){
        when(event){
            is AddEditGoalEvents.OnEditDescription -> {
                description = event.description
            }
            is AddEditGoalEvents.OnEditTitle -> {
                title = event.title
            }
            AddEditGoalEvents.SaveGoal -> {
                viewModelScope.launch {
                    if (title.isBlank()){
                        sendEvent(UiEvents.ShowSnackBar(
                            message = "Title cannot be blank",
                            actionLabel = null
                        ))
                        return@launch
                    }

                    repository.insertGoal(
                        Goal(
                            title = title,
                            description = description,
                            isDone = goal?.isDone?: false,
                            id = goal?.id
                        )
                    )

                    sendEvent(UiEvents.PopBackStack)
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