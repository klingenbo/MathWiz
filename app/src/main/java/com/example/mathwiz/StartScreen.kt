package com.example.mathwiz

import androidx.compose.foundation.clickable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(onStart: (GameConfig) -> Unit) {

    val tables = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp, 20.dp, 30.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Välj gångertabell",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(60.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tables) { table ->
                val color = getColorForTable(table)

                TableButton(
                    table = table,
                    color = color,
                    onClick = { onStart(GameConfig(table.toString())) }
                )
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp, 0.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val color = getColorForTable(11)
            val table = null

            AllTablesButton(
                color = color,
                onClick = { onStart(GameConfig(table.toString())) }
            )
        }
    }
}

@Composable
fun TableButton(
    table: Int,
    color: Color,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.95f else 1f,
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .clickable {
                pressed = true
                onClick()
                pressed = false
            }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$table:an",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

fun getColorForTable(table: Int?): Color {
    return when (table) {
        1 -> Color(0xFFEF9A9A)
        2 -> Color(0xFF90CAF9)
        3 -> Color(0xFFA5D6A7)
        4 -> Color(0xFFFFCC80)
        5 -> Color(0xFFCE93D8)
        6 -> Color(0xFF80CBC4)
        7 -> Color(0xFFFFAB91)
        8 -> Color(0xFFB39DDB)
        9 -> Color(0xFFFFF59D)
        10 -> Color(0xFFB0BEC5)
        else -> Color(0xFFFFB74D)
    }
}

@Composable
fun AllTablesButton(
    color: Color,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.95f else 1f,
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .clickable {
                pressed = true
                onClick()
                pressed = false
            }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Blanda alla",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
