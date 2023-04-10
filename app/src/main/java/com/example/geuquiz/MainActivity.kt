package com.example.geuquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private var correctAnswerCounter = 0.0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_ocean, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex = (currentIndex - 1) % questionBank.size
            }
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        if (questionBank[currentIndex].userAnswer == null) {
            questionBank[currentIndex].userAnswer = userAnswer


            if (userAnswer == correctAnswer) correctAnswerCounter++

            val messageResId = if (userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()

            getResult()
        }
    }

    private fun getResult() {
        if (questionBank.none { it.userAnswer == null }) {
            correctAnswerCounter /= questionBank.size

            val correctAnswerPercent = "%.2f".format(correctAnswerCounter * 100) + "%"
            Toast.makeText(this, correctAnswerPercent, Toast.LENGTH_SHORT)
                .show()
        }
    }

}