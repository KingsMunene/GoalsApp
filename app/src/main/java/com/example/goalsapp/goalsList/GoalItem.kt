package com.example.goalsapp.goalsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goalsapp.data.Goal
import com.example.goalsapp.utils.UiEvents

@Composable
fun GoalItem(
    goal: Goal,
    onEvent:(GoalListEvents) -> Unit,
    onItemClicked: () -> Unit,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onItemClicked)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                   text = goal.title,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = {onEvent(GoalListEvents.DeleteGoal(goal))}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Goal")
                }
            }

            Text(
                text = goal.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Checkbox(
            checked = goal.isDone,
            onCheckedChange = { onEvent(GoalListEvents.ClickGoalDone(goal, isDone = it))}
        )

    }

}