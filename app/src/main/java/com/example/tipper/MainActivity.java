package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat currencyFormat =
            NumberFormat.getNumberInstance();

    private double heightAmount = 0.0; // bill amount entered by the user
    private double weightAmount = 0.0; // bill amount entered by the user
    private TextView amountTextView; // shows formatted bill amount
    private TextView amountTextView2; // shows formatted bill amount
    private TextView totalTextView; // shows calculated tip amount

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        amountTextView2 = (TextView) findViewById(R.id.amountTextView2);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        totalTextView.setText(currencyFormat.format(0));

        // set amountEditText's TextWatcher
        EditText amountEditText =
                (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        EditText amountEditText2 =
                (EditText) findViewById(R.id.amountEditText2);
        amountEditText2.addTextChangedListener(amountEditTextWatcher2);

    }

    // calculate and display tip and total amounts
    private void calculate() {

        // calculate the tip and total
//        double tip = billAmount * 1;
        double total = weightAmount / (heightAmount / 100.00 * heightAmount / 100.00);

        // display tip and total formatted as currency
        totalTextView.setText(currencyFormat.format(total));
    }



    // listener object for the EditText's text-changed events
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                heightAmount = Double.parseDouble(s.toString());
                amountTextView.setText(currencyFormat.format(heightAmount));

            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                amountTextView.setText("");
                heightAmount = 0.0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    private final TextWatcher amountEditTextWatcher2 = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                weightAmount = Double.parseDouble(s.toString());
                amountTextView2.setText(currencyFormat.format(weightAmount));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                amountTextView2.setText("");
                weightAmount = 0.0;
            }

            calculate(); // update the tip and total TextViews
        }


        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
