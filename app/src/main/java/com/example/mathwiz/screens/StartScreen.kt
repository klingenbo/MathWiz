package com.example.mathwiz.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mathwiz.GameConfig
import com.example.mathwiz.R
import com.example.mathwiz.components.AllTablesButton
import com.example.mathwiz.components.TableButton
import com.example.mathwiz.components.TopBar
import com.example.mathwiz.ui.theme.getColorForTable

@Composable
fun StartScreen(onStart: (GameConfig) -> Unit) {

    Scaffold(
        topBar = {
            TopBar()
        },
    ) { innerPadding ->

        val tables = listOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp, 30.dp, 20.dp, 30.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.choose_a_table),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

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

            Spacer(modifier = Modifier.height(30.dp))

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
}
