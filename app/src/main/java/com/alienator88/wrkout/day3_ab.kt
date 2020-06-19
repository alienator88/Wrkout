package com.alienator88.wrkout

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_day3_ab.*

var isRunning = false
var countDownTimer : CountDownTimer?= null

class day3_ab : AppCompatActivity() {
    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        //region Load
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day3_ab)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Tap the plank card to start a 1 minute timer", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val ctGo = findViewById<CardView>(R.id.day3card2_abs)
        //endregion

        //region Perform countdown on cardview tap
        ctGo.setOnClickListener {
            if (!isRunning) {
                if (timerTxt!!.text.toString().isEmpty()) {
                    val snack = Snackbar.make(it,"1 minute plank now",Snackbar.LENGTH_LONG)
                    //snack.show()
                    startCounting()
                } else {
                    val snack = Snackbar.make(it,"Countdown stopped",Snackbar.LENGTH_LONG)
                    //snack.show()
                    stopCounting()
                }
            } else {
                val snack = Snackbar.make(it,"Countdown stopped",Snackbar.LENGTH_LONG)
                //snack.show()
                stopCounting()
            }
        }
        //endregion
    }

    //region Stop the countdown function
    private fun stopCounting() {
        isRunning = false
        timerTxt.setText("");
        countDownTimer!!.cancel()
    }
    //endregion

    //region Start the countdown function
    private fun startCounting() {
        isRunning = true
        countDownTimer = object  : CountDownTimer(60000,1000){
            override fun onFinish() {
                alarm()
                stopCounting()
                timerTxt.setText("");
            }

            override fun onTick(millisUntilFinished: Long) {
                timerTxt.setText("" + millisUntilFinished / 1000);
            }
        }.start()

        countDownTimer!!.start()
    }
    //endregion

    //region Stop countdown if back button is used
    override fun onBackPressed() {
        if (isRunning) {
            //Toast.makeText(this, "Countdown stopped", Toast.LENGTH_SHORT).show()
            stopCounting()
            super.onBackPressed()
        }else{
            //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
    }
    //endregion

    //region Play sound effect on countdown end
    private fun alarm(){
        mp = MediaPlayer.create(this, R.raw.alarm);
        mp?.start();
    }
    //endregion
}