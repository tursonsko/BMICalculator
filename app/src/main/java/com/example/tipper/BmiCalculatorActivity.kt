package com.example.tipper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable
import java.text.DecimalFormat
import java.util.*

// for EditText event handling
// EditText listener
// for bill amount input
// for displaying text
// for currency formatting
/**
 * BMI Calculator
 * Author Wojciech Turek s21611
 */
class BmiCalculatorActivity : AppCompatActivity() {
    private var heightAmount = 0.0 // bill amount entered by the user
    private var weightAmount = 0.0 // bill amount entered by the user
    private var amountTextView // shows formatted bill amount
            : TextView? = null
    private var amountTextView2 // shows formatted bill amount
            : TextView? = null
    private var totalTextView // shows calculated tip amount
            : TextView? = null
    private var bmiResultData: BMIResultData? = null
    private var saveButton // save button
            : Button? = null
    private var chartView // save button
            : Button? = null

    // called when the activity is first created
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.activity_bmi) // inflate the GUI
        bmiResultData = BMIResultData.instance
        // get references to programmatically manipulated TextViews
        amountTextView = findViewById<View>(R.id.amountTextView) as TextView
        amountTextView!!.isFocusable = true
        amountTextView!!.isFocusableInTouchMode = true
        amountTextView2 = findViewById<View>(R.id.amountTextView2) as TextView
        amountTextView2!!.isFocusable = true
        amountTextView2!!.isFocusableInTouchMode = true
        totalTextView = findViewById<View>(R.id.totalTextView) as TextView
        totalTextView!!.text = currencyFormat.format(0)

        // set amountEditText's TextWatcher
        val amountEditText = findViewById<View>(R.id.amountEditText) as EditText
        amountEditText.addTextChangedListener(amountEditTextWatcher)
        val amountEditText2 = findViewById<View>(R.id.amountEditText2) as EditText
        amountEditText2.addTextChangedListener(amountEditTextWatcher2)
        saveButton = findViewById(R.id.saveButton)
        saveButton?.setOnClickListener(View.OnClickListener { v: View? -> saveBMIResult() })
        chartView = findViewById(R.id.graph)
        chartView?.setOnClickListener(View.OnClickListener { v: View? -> startBMIGraphActivity() })
    }

    private fun saveBMIResult() {
        val bmi = totalTextView!!.text.toString().toDouble()
        bmiResultData!!.addBMIResult(bmi)
    }

    private fun startBMIGraphActivity() {
        val intent = Intent(this, BMIGraphActivity::class.java)
        startActivity(intent)
    }

    // calculate and display tip and total amounts
    private fun calculate() {

        // calculate the tip and total
//        double tip = billAmount * 1;
        val total = weightAmount / (heightAmount / 100.00 * heightAmount / 100.00)

        // display tip and total formatted as currency
        totalTextView!!.text = currencyFormat.format(total)
    }

    // listener object for the EditText's text-changed events
    private val amountEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get bill amount and display currency formatted value
                heightAmount = s.toString().toDouble()
                amountTextView!!.text = currencyFormat.format(heightAmount)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                amountTextView!!.text = ""
                heightAmount = 0.0
            }
            calculate() // update the tip and total TextViews
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    private val amountEditTextWatcher2: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get bill amount and display currency formatted value
                weightAmount = s.toString().toDouble()
                amountTextView2!!.text = currencyFormat.format(weightAmount)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                amountTextView2!!.text = ""
                weightAmount = 0.0
            }
            calculate() // update the tip and total TextViews
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }

    class BMIResult(var bmi: Double, var date: Date) : Serializable
    companion object {
        // currency and percent formatter objects
        //    private static final NumberFormat currencyFormat =
        //            NumberFormat.getNumberInstance();
        private val currencyFormat = DecimalFormat("#.##")
    }
}