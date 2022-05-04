package com.example.finalproject

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    var myMediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myMediaPlayer = MediaPlayer.create(this, R.raw.pour)
        myMediaPlayer?.start()
    }

    fun opensecondactivity(view: View){
        val myIntent = Intent(this, DrinkPage::class.java)
        startActivity(myIntent)
    }

    fun openthirdactivity(view: View){
        val myIntent = Intent(this, ActivityThree::class.java)
        startActivity(myIntent)
    }

    fun openfourthactivity(view: View){
        val gmmIntentUri = Uri.parse("geo:0,0?q=bars")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

//    fun openmapsactivity(view: View){
//        val myIntent = Intent(this, MapsActivity::class.java)
//        startActivity(myIntent)
//    }


}