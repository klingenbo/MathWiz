package com.example.mathwiz

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CompleteScreen(
    score: Int,
    answered: Int,
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(70.dp, 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Du matade sköldpaddan!",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center

        )

        Spacer(modifier = Modifier.height(50.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFCE93D8))
            .clip(RoundedCornerShape(16.dp))
            .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Du fick $score rätt \n" +
                        "Du svarade på $answered tal",
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(80.dp))


        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Snyggt kämpat!",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFF90CAF9))
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { }
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Gör igen",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFA5D6A7))
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {  }
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Till start",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

    }
}