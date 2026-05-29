package com.example.mathwiz.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.mathwiz.R

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