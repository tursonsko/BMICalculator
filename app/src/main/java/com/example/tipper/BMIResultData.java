package com.example.tipper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author Wojciech Turek s21611
 */
public class BMIResultData {
    private static BMIResultData instance;
    private List<BmiCalculatorActivity.BMIResult> bmiResults = new ArrayList<>();

    private BMIResultData() {}

    public static BMIResultData getInstance() {
        if (instance == null) {
            instance = new BMIResultData();
        }
        return instance;
    }

    public List<BmiCalculatorActivity.BMIResult> getBMIResults() {
        return bmiResults;
    }

    public void addBMIResult(double bmi) {
        BmiCalculatorActivity.BMIResult result = new BmiCalculatorActivity.BMIResult(bmi, new Date());
        bmiResults.add(result);
    }
}