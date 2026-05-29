package com.example.mathwiz.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.mathwiz.GameViewModel
import com.example.mathwiz.R

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
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("MathWiz")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
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
fun TurtleEating(
    progress: Float,
    score: Int
) {

    val animatedProgress by animateFloatAsState(progress, label = "")

    val startOffset = 120.dp
    val endOffset = (-25).dp
    val isFinished = score >= 10

    val offsetX by animateDpAsState(
        targetValue = if (isFinished) {
            endOffset
        } else {
            lerp(startOffset, endOffset, animatedProgress)
        },
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 20.dp, 30.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(60.dp)
        ) {
            Image(
                // Attribution is required for commercial use
                painter = painterResource(R.drawable.leaf),
                contentDescription = "Leaf"
            )
        }

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(90.dp)
        ) {
            Image(
                // Attribution is required for commercial use
                painter = painterResource(R.drawable.turtle),
                contentDescription = "Turtle",
                modifier = Modifier
                    .offset(x = offsetX)
            )
        }
    }
}