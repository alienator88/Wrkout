package com.alienator88.wrkout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_bmi.*


class bmi : AppCompatActivity() {

    //region Pull color and category map from BMIUtils class
    companion object {
        private val CATEGORY_CODE_MAP: HashMap<Int, String> = HashMap(5)

        init {
            CATEGORY_CODE_MAP.put(R.color.bmi_blue, "Underweight")
            CATEGORY_CODE_MAP.put(R.color.bmi_green, "Healthy")
            CATEGORY_CODE_MAP.put(R.color.bmi_yellow, "Overweight")
            CATEGORY_CODE_MAP.put(R.color.bmi_orange, "Obese")
            CATEGORY_CODE_MAP.put(R.color.bmi_red, "Extremely Obese")
        }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        //region Shared Preferences Setup
        val sp = getSharedPreferences("SP_Data", Context.MODE_PRIVATE)

        fun setupSharedPrefs() {
            val weight = Integer.parseInt(lbsEt.text.toString().trim())
            val heightfeet = Integer.parseInt(feetEt.text.toString().trim())
            val heightinch = Integer.parseInt(inchesET.text.toString().trim())
            val name = name.text.toString().trim()
            //val booleanvar = switchh.isChecked //if checked then true, else false

            //Edit shared preferences (save data)
            val editor = sp.edit()
            editor.clear()
            editor.putInt("WEIGHT", weight)
            editor.putInt("HEIGHT_FEET", heightfeet)
            editor.putInt("HEIGHT_INCHES", heightinch)
            editor.putString("NAME", name)
            //editor.putBoolean("BOOLEAN_VAR", booleanvar)
            editor.apply()
        }

        fun showSharedPrefs() {

            //Get data from shared preferences
            val weightsp = sp.getInt("WEIGHT", 0)
            val heightfeetsp = sp.getInt("HEIGHT_FEET", 0)
            val heightinchsp = sp.getInt("HEIGHT_INCHES", 0)
            val name = sp.getString("NAME", "User")
            //val booleanvar = sp.getBoolean("BOOLEAN_VAR", false)

            val weight = weightsp.toDouble()
            val height = (heightfeetsp.toDouble() * 12) + heightinchsp.toDouble()

            // Calculate bmi
            val bmiDetails = BMIUtils.calculateBMI(weight, height)

            // Fetch the category identifier color code
            val categoryIdentifierColorCode: Int = BMIUtils.getCategoryIdentifier(bmiDetails.second)

            // Update the TextView content
            bmiTv.text = "Your BMI is: " + bmiDetails.first

            //Show data in results text view
            resultsTv.text = "Height: $heightfeetsp.$heightinchsp ft\nWeight: $weightsp"

            // Update Category TextView's text and background
            updateViews(CATEGORY_CODE_MAP[categoryIdentifierColorCode], categoryIdentifierColorCode)

            //Enter BMI into SharedPreferences
            val editor = sp.edit()
            editor.putString("BMI", bmiDetails.first)
            editor.apply()


        }

        //endregion

        //region Handle Save Button Click: Input data and save in shared preferences
        saveBtn.setOnClickListener {

            if (lbsEt.text.toString().trim().isEmpty() or feetEt.text.toString().trim().isEmpty() or inchesET.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Fill in all the fields first", Toast.LENGTH_SHORT).show()
            } else {
                setupSharedPrefs()
                showSharedPrefs()
                Toast.makeText(this, "BMI information has been stored", Toast.LENGTH_SHORT).show()
            }
        }
        //endregion

    }

    //region Update Views
    private fun updateViews(category: String?, colorCode: Int) {
        bmiZone.text = category
        bmiZone.setTextColor(ContextCompat.getColor(this, colorCode))
    }
    //endregion
}

//region Formulas
/*
BMI Formula Imperial - (lbs x 703) / (inches x inches)
BMI Formula Metric   - kg / (meters x meters)
*/
//endregion