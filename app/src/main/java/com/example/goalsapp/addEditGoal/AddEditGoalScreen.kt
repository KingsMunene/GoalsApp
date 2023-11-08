package com.example.goalsapp.addEditGoal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalsapp.utils.UiEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditGoalScreen(
    popBackStack: () -> Unit,
    viewModel: AddEditGoalViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = 1){
       viewModel.uiEvents.collect{event ->
           when(event){
               UiEvents.PopBackStack -> popBackStack()
               is UiEvents.ShowSnackBar -> {
                   snackbarHostState.showSnackbar(
                       message = event.message,
                       actionLabel = null
                   )
               }
               else -> Unit
           }
       }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.onEvent(AddEditGoalEvents.SaveGoal)}) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Save Goal"
                )
            }
        }
    ) {_->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = viewModel.title,
                onValueChange = { viewModel.onEvent(AddEditGoalEvents.OnEditTitle(it)) },
                singleLine = true,
                placeholder = { Text(text = "Title")}
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = viewModel.description,
                onValueChange = { viewModel.onEvent(AddEditGoalEvents.OnEditDescription(it)) },
                singleLine = false,
                maxLines = 5,
                placeholder = { Text(text = "Description")}
            )

        }

    }

}