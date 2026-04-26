package com.example.mathwiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompleteScreen(score: Int, answered: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Session complete",
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            text = "Du fick $score rätt",
            style = MaterialTheme.typography.displayMedium
        )

        Text(
            text = "Du svarade på $answered tal",
            style = MaterialTheme.typography.displayMedium
        )
    }
}