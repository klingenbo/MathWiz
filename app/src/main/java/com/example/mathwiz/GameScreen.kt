package com.example.mathwiz

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    table: Int?
) {
    val question by viewModel.question.collectAsState()
    val score by viewModel.score.collectAsState()
    val progress by viewModel.progress.collectAsState()
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    var bounce by remember { mutableStateOf(false) }

    val color = getColorForTable(table)

    LaunchedEffect(question) {
        selectedAnswer = null
    }

    LaunchedEffect(progress) {
        if (progress >= 0.99f) {
            bounce = true
            delay(150)
            bounce = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${question.a} × ${question.b}",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

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
                        viewModel.onAnswerSelected(option)
                    }
                )
            }
        }

        TurtleEating(progress = progress, bounce = bounce)
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
fun TurtleEating(progress: Float, bounce: Boolean) {

    val animatedProgress by animateFloatAsState(progress, label = "")

    val startOffset = 120.dp
    val endOffset = 0.dp

    // turtle touched leaf with smooth transition
    val offsetX by animateDpAsState(
        targetValue = lerp(startOffset, endOffset, animatedProgress),
        label = ""
    )

    val scale by animateFloatAsState(
        targetValue = if (bounce) 1.3f else 1f,
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(40.dp)
        ) {
            Text("🌿", fontSize = MaterialTheme.typography.displaySmall.fontSize)
        }

        Text(
            text = "🐢",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .offset(x = offsetX)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        )
    }
}