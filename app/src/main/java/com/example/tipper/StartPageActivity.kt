package com.example.tipper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tipper.BmiCalculatorActivity

/**
 * Author Wojciech Turek s21611
 */
class StartPageActivity : AppCompatActivity() {
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)
        button1 = findViewById(R.id.button1)
        button1?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StartPageActivity, BmiCalculatorActivity::class.java)
            startActivity(intent)
        })
        button2 = findViewById(R.id.button2)
        button2?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StartPageActivity, CaloriesCalculatorActivity::class.java)
            startActivity(intent)
        })
        button3 = findViewById(R.id.button3)
        button3?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StartPageActivity, RecipesActivity::class.java)
            startActivity(intent)
        })
        button4 = findViewById(R.id.button4)
        button4?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StartPageActivity, QuizActivity::class.java)
            startActivity(intent)
        })
    }
}