package com.example.tipper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Author Wojciech Turek s21611
 */
public class ChickenAlfredoActivity extends AppCompatActivity {

    private TextView recipeTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_alfredo);

        recipeTextView = findViewById(R.id.recipeTextView);
        backButton = findViewById(R.id.backButton);

        recipeTextView.setText(getString(R.string.chicken_alfredo_recipe));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}