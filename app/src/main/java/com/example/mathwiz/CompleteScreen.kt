package com.example.mathwiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CompleteScreen(
    score: Int,
    answered: Int,
    table: Int?,
    highScore: Int,
    openStart: () -> Unit,
    playAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(70.dp, 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gångertabell $table",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center

        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color(0xFFCE93D8))
                .clip(RoundedCornerShape(16.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Du fick $score/$answered" +
                        "\n\nDitt bästa resultat = $highScore/$answered",
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(50.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.snyggt_kampat),
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = { playAgain() },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFF6650A4),
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.gor_igen),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = { openStart() },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFF6650A4),
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.till_start),
                style = MaterialTheme.typography.headlineMedium
            )
        }

    }
}