package com.example.finalproject

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_three.*

class ActivityThree : AppCompatActivity() {
    private val favList = ArrayList<String?>() // This is the list of tasks
    lateinit var myAdapter: ArrayAdapter<String> // Adapter for the ListView
    private val FILE_NAME = "favList" // File name for shared preferences
    var myMediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)

        // ListView adapter logic
        myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favList)
        favs.adapter = myAdapter

        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val tasks = sharedPreferences.getString("favs", "") ?: ""
        Log.d("favsList", tasks)

        if (tasks.isNotEmpty()){
            // Create an instance of Gson
            val gson = Gson()
            // create an object expression that descends from TypeToken
            // and then get the Java Type from that
            val sType = object : TypeToken<List<String>>() { }.type
            // provide the type specified above to fromJson() method
            // this will deserialize the previously saved Json into an object of the specified type (e.g., list)
            val savedTaskList = gson.fromJson<List<String>>(tasks, sType)

            favList.addAll(savedTaskList) // add the data to the list
            myAdapter.notifyDataSetChanged()
        }

        favs.setOnItemClickListener { list, item, position, id ->
            // Determine which item in the list is selected
            val selectedItem = list.getItemAtPosition(position).toString()
            Log.d("TAGI", "$selectedItem")

            // https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin

            val myIntent = Intent(this, ActivitySix::class.java)
            myIntent.putExtra("drink", selectedItem)
            startActivity(myIntent)
        }

        // Remove the task from the taskList on a long press
        favs.setOnItemLongClickListener {parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Removing $selectedItem", Toast.LENGTH_SHORT).show()
            favList.removeAt(position)
            val FILE_NAME = "favs"
            saveArrayList(favList, FILE_NAME)
            // update shared preferences

                myMediaPlayer = MediaPlayer.create(this, R.raw.unlike)
                myMediaPlayer?.start()

            myAdapter.notifyDataSetChanged()

            return@setOnItemLongClickListener true
        }
    }
    // helper function to save https://stackoverflow.com/questions/7057845/save-arraylist-to-sharedpreferences
    fun saveArrayList(list: java.util.ArrayList<String?>?, key: String?) {
        val prefs = getSharedPreferences(FILE_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        val editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }
}