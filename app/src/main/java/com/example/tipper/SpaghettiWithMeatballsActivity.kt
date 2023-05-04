package com.example.tipper

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Author Wojciech Turek s21611
 */
class SpaghettiWithMeatballsActivity : AppCompatActivity() {
    private var recipeTextView: TextView? = null
    private var backButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spaghetti_meatballs)
        recipeTextView = findViewById(R.id.recipeTextView)
        backButton = findViewById(R.id.backButton)
        recipeTextView?.setText(getString(R.string.spaghetti_meatballs_recipe))
        backButton?.setOnClickListener(View.OnClickListener { finish() })
    }
}