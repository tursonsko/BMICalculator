package com.example.tipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Author Wojciech Turek s21611
 */
public class RecipesActivity extends AppCompatActivity {

    private Button recipe1Button;
    private Button recipe2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        recipe1Button = findViewById(R.id.recipe1Button);
        recipe2Button = findViewById(R.id.recipe2Button);

        recipe1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesActivity.this, SpaghettiWithMeatballsActivity.class);
                startActivity(intent);
            }
        });

        recipe2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipesActivity.this, ChickenAlfredoActivity.class);
                startActivity(intent);
            }
        });
    }
}
