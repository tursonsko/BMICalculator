package com.example.tipper

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * Author Wojciech Turek s21611
 */
class CaloriesCalculatorActivity : AppCompatActivity() {
    private var ageEditText: EditText? = null
    private var weightEditText: EditText? = null
    private var heightEditText: EditText? = null
    private var genderRadioGroup: RadioGroup? = null
    private var maleRadioButton: RadioButton? = null
    private var femaleRadioButton: RadioButton? = null
    private var calculateButton: Button? = null
    private var resultTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)
        ageEditText = findViewById(R.id.et_age)
        weightEditText = findViewById(R.id.et_weight)
        heightEditText = findViewById(R.id.et_height)
        genderRadioGroup = findViewById(R.id.rg_gender)
        maleRadioButton = findViewById(R.id.rb_male)
        femaleRadioButton = findViewById(R.id.rb_female)
        calculateButton = findViewById(R.id.btn_calculate)
        resultTextView = findViewById(R.id.tv_calories_result)
        calculateButton?.setOnClickListener(View.OnClickListener { calculateCalories() })
    }

    private fun calculateCalories() {
        // get input values
        val age = ageEditText!!.text.toString().toInt()
        val weight = weightEditText!!.text.toString().toFloat()
        val height = heightEditText!!.text.toString().toInt()
        val isMale = maleRadioButton!!.isChecked

        // calculate BMR
        val bmr: Double
        bmr = if (isMale) {
            88.362 + 13.397 * weight + 4.799 * height - 5.677 * age
        } else {
            447.593 + 9.247 * weight + 3.098 * height - 4.330 * age
        }
        resultTextView!!.text = getString(R.string.calories_result, bmr)
    }
}