package com.alienator88.wrkout

import java.math.RoundingMode
import java.text.DecimalFormat

class BMIUtils private constructor() {

    companion object {

        fun calculateBMI(weight: Double, height: Double): Pair<String, Int> {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.HALF_UP

            val bmi = (weight * 703)  / (height * height)
            return Pair(df.format(bmi), bmi.toInt())
        }

        fun getCategoryIdentifier(bmiVal: Int): Int {
            val categoryColorCode: Int

            when (bmiVal) {
                in 0..18 -> {
                    categoryColorCode = R.color.bmi_blue
                }
                in 19..24 -> {
                    categoryColorCode = R.color.bmi_green
                }
                in 25..29 -> {
                    categoryColorCode = R.color.bmi_yellow
                }
                in 30..39 -> {
                    categoryColorCode = R.color.bmi_orange
                }
                else -> {
                    categoryColorCode = R.color.bmi_red
                }
            }
            return categoryColorCode
        }
    }
}

/*
BMI Formula Imperial - (lbs x 703) / (inches x inches)
BMI Formula Metric   - kg / (meters x meters)
*/