package com.example.mathwiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathwiz.datastore.SavedHighScoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class Question(
    val a: Int,
    val b: Int,
    val correctAnswer: Int,
    val options: List<Int>
)

class GameViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var table: String? = null

    private val _question = MutableStateFlow(generateQuestion())
    val question = _question

    private val _score = MutableStateFlow(0)
    val score = _score

    private val repository = SavedHighScoreRepository(getApplication())

    private val _highScore = MutableStateFlow(0)
    val highScore = _highScore

    init {
        viewModelScope.launch {
            repository.getHighScore().collect {
                _highScore.value = it
            }
        }
    }

    fun setConfig(config: GameConfig) {
        table = config.table
        _question.value = generateQuestion()
    }

    val progress = _score.map { score ->
        (score % 10) / 10f
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0f
    )

    private val _isCompleted = MutableStateFlow(false)
    val isCompleted = _isCompleted

    private val _questionsAnswered = MutableStateFlow(0)
    val questionsAnswered = _questionsAnswered


    fun onAnswerSelected(answer: Int) {
        val correct = _question.value.correctAnswer

        if (answer == correct) {
            _score.value += 1
        }

        questionsAnswered.value += 1

        viewModelScope.launch {
            if (questionsAnswered.value == 10) {

                if (_score.value > _highScore.value) {
                    repository.saveHighScore(_score.value)
                }

                delay(500)

                isCompleted.value = true

                return@launch
            } else {
                delay(1000)

                _question.value = generateQuestion()
            }
        }
    }

    private fun generateQuestion(): Question {

        val a = table?.toIntOrNull() ?: (1..10).random()
        val b = (1..10).random()

        val correct = a * b

        val options = mutableSetOf(correct)

        while (options.size < 4) {
            options.add((1..100).random())
        }

        return Question(
            a = a,
            b = b,
            correctAnswer = correct,
            options = options.shuffled()
        )
    }

    fun isCorrect(option: Int, selectedAnswer: Int?): Boolean? {
        val correct = _question.value.correctAnswer

        return when {
            selectedAnswer == null -> null

            option == correct -> true // visa rätt alltid

            option == selectedAnswer -> false // visa fel val

            else -> null
        }
    }
}