package com.example.mathwiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.WindowRecomposerPolicy
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mathwiz.ui.theme.MathWizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathWizTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "start"
                ) {

                    composable("start") {
                        StartScreen { config ->
                            val tableArg = config.table ?: "all"
                            navController.navigate("game/$tableArg")
                        }
                    }

                    composable("game/{table}") { backStackEntry ->
                        val tableArg = backStackEntry.arguments?.getString("table")
                        val table = if (tableArg == "all") null else tableArg

                        val viewModel = remember { GameViewModel() }

                        val score by viewModel.score.collectAsState()
                        val answered by viewModel.questionsAnswered.collectAsState()

                        LaunchedEffect(table) {
                            viewModel.setConfig(GameConfig(table))
                        }

                        GameScreen(
                            viewModel,
                            table?.toIntOrNull(),
                            onGameComplete = {
                                navController.navigate(
                                    "complete/$score/$answered"
                                )
                            })
                    }

                    composable("complete/{score}/{answered}") { backStackEntry ->
                        val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
                        val answered = backStackEntry.arguments?.getString("answered")?.toInt() ?: 0

                        CompleteScreen(score, answered)
                    }
                }
            }
        }
    }
}
