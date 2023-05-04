package com.example.tipper

import com.example.tipper.BmiCalculatorActivity.BMIResult
import java.util.*

/**
 * Author Wojciech Turek s21611
 */
class BMIResultData private constructor() {
    private val bmiResults: MutableList<BMIResult> = ArrayList()
    val bMIResults: List<BMIResult>
        get() = bmiResults

    fun addBMIResult(bmi: Double) {
        val result = BMIResult(bmi, Date())
        bmiResults.add(result)
    }

    companion object {
        var instance: BMIResultData? = null
            get() {
                if (field == null) {
                    field = BMIResultData()
                }
                return field
            }
            private set
    }
}