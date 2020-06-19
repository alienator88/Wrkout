package com.alienator88.wrkout

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custompopup.view.*
import java.security.AccessController.getContext
import java.text.SimpleDateFormat
import java.util.*
import android.view.ContextThemeWrapper as ContextThemeWrapper1


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region Variables
        val name = findViewById<TextView>(R.id.welcometxt)
        val date = findViewById<TextView>(R.id.datetxt)
        val day1Go = findViewById<LinearLayout>(R.id.day1_stack)
        val day2Go = findViewById<LinearLayout>(R.id.day2_stack)
        val day3Go = findViewById<LinearLayout>(R.id.day3_stack)
        val day4Go = findViewById<LinearLayout>(R.id.day4_stack)
        val infoGo = findViewById<LinearLayout>(R.id.moreinfo)
        val bmiGo = findViewById<LinearLayout>(R.id.bmiGo) // click bmi text to open bmi window
        val sp = getSharedPreferences("SP_Data", Context.MODE_PRIVATE)
        val message = sp.getString("BMI", "")
        val nameSP = sp.getString("NAME", "")

        //endregion

        //region CheckBMI and name on load
        if (nameSP.isNullOrEmpty()) {
            name.text = "Welcome, User"
        } else {
            name.text = "Welcome, " + nameSP
        }

            if (message.isNullOrEmpty()) {
                mainBMI.text =
                    "Your BMI has not been calculated yet. Tap here to enter, then tap the pencil to update this screen!"
            } else {
                mainBMI.text = "Your current BMI is: " + message
            }
            //endregion

            //region Date
            val date_n = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
            date.text = date_n
            //endregion

            //region Click Listeners

            bmiGo.setOnClickListener {
                val intent = Intent(this, com.alienator88.wrkout.bmi::class.java)
                startActivity(intent)
            }

            day1Go.setOnClickListener {
                val intent = Intent(this, com.alienator88.wrkout.day1_ab::class.java)
                startActivity(intent)
            }

            day2Go.setOnClickListener {
                val intent = Intent(this, com.alienator88.wrkout.day2_ab::class.java)
                startActivity(intent)
            }

            day3Go.setOnClickListener {
                val intent = Intent(this, com.alienator88.wrkout.day3_ab::class.java)
                startActivity(intent)
            }

            day4Go.setOnClickListener {
                val intent = Intent(this, com.alienator88.wrkout.day4_ab::class.java)
                startActivity(intent)
            }

            infoGo.setOnClickListener {
             /*   // Inflate the dialog with custom view
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.custompopup, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                    .setView(mDialogView)
                //.setTitle("More Workouts")
                //show dialog
                val mAlertDialog = mBuilder.show()
                //login button click of custom layout
                mDialogView.okbtn.setOnClickListener() {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }  */
                val dialog = BottomSheetDialog(this)
                val bottomSheet = layoutInflater.inflate(R.layout.custompopup, null)
                bottomSheet.forearms.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-forearms")
                    startActivity(url)
                }
                bottomSheet.cardio.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-cardio")
                    startActivity(url)
                }
                bottomSheet.calves.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-calves")
                    startActivity(url)
                }
                bottomSheet.triceps.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-triceps")
                    startActivity(url)
                }
                bottomSheet.biceps.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-biceps")
                    startActivity(url)
                }
                bottomSheet.shoulders.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-shoulders")
                    startActivity(url)
                }
                bottomSheet.legs.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-legs")
                    startActivity(url)
                }
                bottomSheet.back.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-back")
                    startActivity(url)
                }
                bottomSheet.abs.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-abs")
                    startActivity(url)
                }
                bottomSheet.chest.setOnClickListener {
                    val url = Intent(android.content.Intent.ACTION_VIEW)
                    url.data = Uri.parse("http://www.jasestuart.com/exercise-database-chest")
                    startActivity(url)
                }
                dialog.setContentView(bottomSheet)
                dialog.show()

            }

            //endregion

        }

    //region Override Restart
    override fun onRestart() {
        super.onRestart()
        val sp = getSharedPreferences("SP_Data", Context.MODE_PRIVATE)
        val message = sp.getString("BMI", "")
        val nameSP = sp.getString("NAME", "")
        val name = findViewById<TextView>(R.id.welcometxt)

        name.text = "Welcome, " + nameSP

        if (message.isNullOrEmpty()) {
            mainBMI.text = "Your BMI has not been calculated yet. Tap here to enter, then tap the pencil to update this screen!"
        } else {
            mainBMI.text = "Your current BMI is: " + message
        }
    }
    //endregion




}





