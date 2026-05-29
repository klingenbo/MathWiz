package com.example.mathwiz.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val USER_PREFERENCES_HIGH_SCORE = "user_high_score"

private val Context.datastore by preferencesDataStore(
    name = USER_PREFERENCES_HIGH_SCORE
)

class SavedHighScoreRepository(
    private val context: Context
) {

    private object Keys {
        val USER_HIGH_SCORE = intPreferencesKey("user_high_score")
    }

    fun getHighScore(): Flow<Int> {
        return context.datastore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preferences ->
                preferences[Keys.USER_HIGH_SCORE] ?: 0
            }
    }

    suspend fun saveHighScore(score: Int) {
        context.datastore.edit { preferences ->
            preferences[Keys.USER_HIGH_SCORE] = score
        }
    }
}