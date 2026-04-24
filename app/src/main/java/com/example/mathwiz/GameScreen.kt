package com.example.mathwiz

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    table: Int?
) {

    val question by viewModel.question.collectAsState()
    val score by viewModel.score.collectAsState()
    val progress by viewModel.progress.collectAsState()
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    val color = getColorForTable(table)

    LaunchedEffect(question) {
        selectedAnswer = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // 🟢 Score uppe
        /* Text(
             text = "Poäng: $score",
             modifier = Modifier
                 .fillMaxWidth()
                 .align(Alignment.CenterHorizontally)
                 .padding(top = 50.dp, start = 20.dp),
             style = MaterialTheme.typography.titleLarge
         ) */

        // 🟢 spelinnehåll
        Box(modifier = Modifier.weight(1f)) {
            // fråga + svar här
        }

        // 🐢 turtle + leaf
        TurtleFeeding(progress = progress)

        Spacer(modifier = Modifier.weight(1f))

        // 🟣 Frågan i mitten
        Text(
            text = "${question.a} × ${question.b}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        LazyColumn {
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
                        viewModel.onAnswerSelected(option)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun AnswerButton(
    text: String,
    isCorrect: Boolean?,
    baseColor: Color,
    onClick: () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = when (isCorrect) {
            true -> Color.Green
            false -> Color.Red
            else -> baseColor
        },
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(bgColor.copy(alpha = 0.2f))
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .clickable { onClick() }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun TurtleFeeding(progress: Float) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // 🌿 blad (som "äts upp")
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(20.dp)
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .background(Color.Green)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 🐢 (placeholder)
        Text("🐢", style = MaterialTheme.typography.displayMedium)
    }
}