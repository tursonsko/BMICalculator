package com.example.tipper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Wojciech Turek s21611
 */
public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        nextButton = findViewById(R.id.next_button);

        // Load questions from JSON file
        String json = loadJSONFromAsset("questions.json");
        Gson gson = new Gson();
        QuestionList questionListObject = gson.fromJson(json, QuestionList.class);
        questionList = questionListObject.getQuestions();

        // Display first question
        displayQuestion(currentQuestionIndex);

        // Set up button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an answer was selected
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                if (selectedOptionId == -1) {
                    return;
                }
                // Check if answer is correct
                RadioButton selectedOption = findViewById(selectedOptionId);
                String selectedAnswer = selectedOption.getText().toString();
                String correctAnswer = questionList.get(currentQuestionIndex).getAnswer();

                if (selectedAnswer.equals(correctAnswer)) {
                    score++;
                }
                // Go to next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    displayQuestion(currentQuestionIndex);
                } else {
                    endQuiz(score);
                }
            }
        });
    }

    private void displayQuestion(int questionIndex) {
        // Set question text
        String questionText = questionList.get(questionIndex).getQuestion();
        questionTextView.setText(questionText);
        // Set options
        List<String> options = questionList.get(questionIndex).getOptions();
        optionsRadioGroup.removeAllViews();
        for (int i = 0; i < options.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options.get(i));
            optionsRadioGroup.addView(radioButton);
        }
        // Clear selection
        optionsRadioGroup.clearCheck();
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream inputStream = getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void endQuiz(int score) {

        // Display the score
        String message = "Quiz ended. Your score: " + score + " out of " + questionList.size();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Allow the user to start over or exit the app
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz ended");
        builder.setMessage(message);
        builder.setPositiveButton("Start over", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reset the quiz
                currentQuestionIndex = 0;
                for (Question question : questionList) {
                    question.setAnswered(false);
                }
                displayQuestion(currentQuestionIndex);
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Exit the app
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private class QuestionList {
        private List<Question> questions;

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }
    }
}