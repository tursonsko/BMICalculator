package com.example.tipper

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.IOException

/**
 * Author Wojciech Turek s21611
 */
class QuizActivity : AppCompatActivity() {
    private var questionTextView: TextView? = null
    private var optionsRadioGroup: RadioGroup? = null
    private var nextButton: Button? = null
    private var currentQuestionIndex = 0
    private var score = 0
    private var questionList: List<Question>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize views
        questionTextView = findViewById(R.id.question_text_view)
        optionsRadioGroup = findViewById(R.id.options_radio_group)
        nextButton = findViewById(R.id.next_button)

        // Load questions from JSON file
        val json = loadJSONFromAsset("questions.json")
        val gson = Gson()
        val questionListObject = gson.fromJson(json, QuestionList::class.java)
        questionList = questionListObject.questions

        // Display first question
        displayQuestion(currentQuestionIndex)

        // Set up button click listener
        nextButton?.setOnClickListener(View.OnClickListener { // Check if an answer was selected
            val selectedOptionId = optionsRadioGroup?.getCheckedRadioButtonId()
            if (selectedOptionId == -1) {
                return@OnClickListener
            }
            // Check if answer is correct
            val selectedOption = findViewById<RadioButton>(selectedOptionId!!)
            val selectedAnswer = selectedOption.text.toString()
            val correctAnswer = questionList!![currentQuestionIndex].answer
            if (selectedAnswer == correctAnswer) {
                score++
            }
            // Go to next question
            currentQuestionIndex++
            if (currentQuestionIndex < questionList!!.size) {
                displayQuestion(currentQuestionIndex)
            } else {
                endQuiz(score)
            }
        })
    }

    private fun displayQuestion(questionIndex: Int) {
        // Set question text
        val questionText = questionList!![questionIndex].question
        questionTextView!!.text = questionText
        // Set options
        val options = questionList!![questionIndex].options
        optionsRadioGroup!!.removeAllViews()
        for (i in options!!.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = options[i]
            optionsRadioGroup!!.addView(radioButton)
        }
        // Clear selection
        optionsRadioGroup!!.clearCheck()
    }

    private fun loadJSONFromAsset(fileName: String): String? {
        var json: String? = null
        json = try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun endQuiz(score: Int) {

        // Display the score
        val message = "Quiz ended. Your score: " + score + " out of " + questionList!!.size
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // Allow the user to start over or exit the app
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quiz ended")
        builder.setMessage(message)
        builder.setPositiveButton("Start over") { dialog, which -> // Reset the quiz
            currentQuestionIndex = 0
            for (question in questionList!!) {
                question.isAnswered = false
            }
            displayQuestion(currentQuestionIndex)
        }
        builder.setNegativeButton("Exit") { dialog, which -> // Exit the app
            finish()
        }
        builder.setCancelable(false)
        builder.show()
    }

    private inner class QuestionList {
        var questions: List<Question>? = null
    }
}