package com.example.mathwiz.ui.theme

import androidx.compose.ui.graphics.Color

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