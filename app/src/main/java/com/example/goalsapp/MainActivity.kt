package com.example.goalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.goalsapp.addEditGoal.AddEditGoalScreen
import com.example.goalsapp.goalsList.GoalListScreen
import com.example.goalsapp.ui.theme.GoalsAppTheme
import com.example.goalsapp.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoalsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Routes.GoalList.name){
                        composable(route = Routes.GoalList.name) {
                            GoalListScreen(
                                navigate = { navController.navigate(it.route) }
                            )
                        }

                        composable(
                            route = Routes.AddEditGoalItem.name + "?goalId={goalId}",
                            arguments =listOf(
                               navArgument(name = "goalId"){
                                   type = NavType.IntType
                                   defaultValue = -1
                               }
                            )
                        ){
                            AddEditGoalScreen(popBackStack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoalsAppTheme {

    }
}