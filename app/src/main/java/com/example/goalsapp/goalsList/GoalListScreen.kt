package com.example.goalsapp.goalsList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalsapp.utils.UiEvents
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalListScreen(
    navigate:(UiEvents.Navigate) -> Unit,
    viewModel: GoalsListViewModel = hiltViewModel()
) {
    val snackbarHostState = remember{ SnackbarHostState() }

    val goalList = viewModel.goalsList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = 1 ){
        viewModel.uiEvents.collect{event ->
            when(event){
                is UiEvents.Navigate -> navigate(event)
                is UiEvents.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.actionLabel
                    )

                    if (result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(GoalListEvents.UndoDeleteGoal)
                    }
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.onEvent(GoalListEvents.AddNewGoal) }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add new Goal" )
            }
        }
    ) {_->
        LazyColumn{
            items(goalList.value){goal ->
                GoalItem(
                    goal = goal,
                    onEvent = viewModel::onEvent,
                    onItemClicked = { viewModel.onEvent(GoalListEvents.GoalClicked(goal)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

}