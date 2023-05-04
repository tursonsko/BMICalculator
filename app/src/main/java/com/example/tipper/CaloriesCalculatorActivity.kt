package com.example.tipper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Author Wojciech Turek s21611
 */
public class CaloriesCalculatorActivity extends AppCompatActivity {

    private EditText ageEditText;
    private EditText weightEditText;
    private EditText heightEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        ageEditText = findViewById(R.id.et_age);
        weightEditText = findViewById(R.id.et_weight);
        heightEditText = findViewById(R.id.et_height);
        genderRadioGroup = findViewById(R.id.rg_gender);
        maleRadioButton = findViewById(R.id.rb_male);
        femaleRadioButton = findViewById(R.id.rb_female);
        calculateButton = findViewById(R.id.btn_calculate);
        resultTextView = findViewById(R.id.tv_calories_result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalories();
            }
        });
    }

    private void calculateCalories() {
        // get input values
        int age = Integer.parseInt(ageEditText.getText().toString());
        float weight = Float.parseFloat(weightEditText.getText().toString());
        int height = Integer.parseInt(heightEditText.getText().toString());
        boolean isMale = maleRadioButton.isChecked();

        // calculate BMR
        double bmr;
        if (isMale) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        resultTextView.setText(getString(R.string.calories_result, bmr));
    }
}
