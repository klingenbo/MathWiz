package com.example.mathwiz.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mathwiz.GameViewModel
import com.example.mathwiz.components.AnswerButton
import com.example.mathwiz.components.TopBar
import com.example.mathwiz.components.TurtleEating
import com.example.mathwiz.ui.theme.getColorForTable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel,
    table: Int?,
    onGameComplete: () -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onBack = onBackClicked
            )
        },
    ) { innerPadding ->

        val question by viewModel.question.collectAsState()
        val score by viewModel.score.collectAsState()
        val progress by viewModel.progress.collectAsState()
        val isCompleted by viewModel.isCompleted.collectAsState()
        var selectedAnswer by remember { mutableStateOf<Int?>(null) }

        val color = getColorForTable(table)

        LaunchedEffect(question) {
            selectedAnswer = null
        }

        LaunchedEffect(isCompleted) {
            if (isCompleted) {
                onGameComplete()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TurtleEating(
                progress = progress,
                score = score
            )

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { 1f - progress },
                color = color,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 75.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${question.a} × ${question.b}",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            LazyColumn(
                modifier = Modifier.weight(2f),
            ) {
                items(question.options) { option ->
                    AnswerButton(
                        text = option.toString(),
                        isCorrect = viewModel.isCorrect(
                            option = option,
                            selectedAnswer = selectedAnswer
                        ),
                        baseColor = color,
                        onClick = {
                            selectedAnswer = option
                            viewModel.onAnswerSelected(
                                answer = option,
                            )
                        }
                    )
                }
            }
        }
    }
}