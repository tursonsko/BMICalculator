package com.example.tipper

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tipper.BMIResultAdapter.BMIResultViewHolder
import com.example.tipper.BmiCalculatorActivity.BMIResult
import java.text.DateFormat
import java.text.DecimalFormat
import java.util.*

/**
 * Author Wojciech Turek s21611
 */
class BMIResultAdapter(private val bmiResults: List<BMIResult>) : RecyclerView.Adapter<BMIResultViewHolder>() {
    init {
        Collections.sort(bmiResults) { o1, o2 -> o2.date.compareTo(o1.date) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BMIResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bmi_result_item, parent, false)
        return BMIResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: BMIResultViewHolder, position: Int) {
        val result = bmiResults[position]
        holder.bmiValueTextView.text = decimalFormat.format(result.bmi)
        holder.dateTextView.text = dateFormat.format(result.date)
        holder.itemView.setBackgroundColor(Color.parseColor(holder.itemView.context.getString(COLOR_RES_IDS[getBMIStatus(result.bmi)])))
    }

    override fun getItemCount(): Int {
        return bmiResults.size
    }

    private fun getBMIStatus(bmi: Double): Int {
        return if (bmi < 16) {
            0
        } else if (bmi >= 16 && bmi <= 16.99) {
            1
        } else if (bmi >= 17 && bmi <= 18.49) {
            2
        } else if (bmi >= 18.5 && bmi <= 24.99) {
            3
        } else if (bmi >= 25 && bmi <= 29.99) {
            4
        } else if (bmi >= 30 && bmi <= 34.99) {
            5
        } else if (bmi >= 35 && bmi <= 39.99) {
            6
        } else {
            7
        }
    }

    class BMIResultViewHolder(itemView: View) : ViewHolder(itemView) {
        var bmiValueTextView: TextView
        var dateTextView: TextView

        init {
            bmiValueTextView = itemView.findViewById(R.id.bmiValueTextView)
            dateTextView = itemView.findViewById(R.id.dateTextView)
        }
    }

    companion object {
        private val decimalFormat = DecimalFormat("#.##")
        private val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        private val COLOR_RES_IDS = intArrayOf(
                R.color.colorEmaciation,
                R.color.colorUnderweight,
                R.color.colorLowWeight,
                R.color.colorNormalWeight,
                R.color.colorOverweight,
                R.color.colorFirstDegreeObesity,
                R.color.colorSecondDegreeObesity,
                R.color.colorExtremeObesity
        )
    }
}