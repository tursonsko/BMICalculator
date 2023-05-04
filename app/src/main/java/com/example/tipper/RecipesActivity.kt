package com.example.tipper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Author Wojciech Turek s21611
 */
class RecipesActivity : AppCompatActivity() {
    private var recipe1Button: Button? = null
    private var recipe2Button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        recipe1Button = findViewById(R.id.recipe1Button)
        recipe2Button = findViewById(R.id.recipe2Button)
        recipe1Button?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@RecipesActivity, SpaghettiWithMeatballsActivity::class.java)
            startActivity(intent)
        })
        recipe2Button?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@RecipesActivity, ChickenAlfredoActivity::class.java)
            startActivity(intent)
        })
    }
}