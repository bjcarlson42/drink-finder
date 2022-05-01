package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.DrinkData
import com.example.finalproject.Fetch
import com.example.finalproject.R
import kotlinx.android.synthetic.main.activity_five.*
import kotlinx.android.synthetic.main.activity_two.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DrinkPage : AppCompatActivity() {

    private val ingredientsList = listOf("Light rum", "Applejack", "Gin", "Dark rum")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredientsList)
        val drinkList = findViewById<ListView>(R.id.ingredient_list_id)
        drinkList.adapter = myAdapter

        drinkList.setOnItemClickListener { list, item, position, id ->

            // Determine which item in the list is selected
            val selectedItem = list.getItemAtPosition(position).toString()
            Log.d("TAGI", "$selectedItem")

            // https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin

            val myIntent = Intent(this, ActivityFive::class.java)
            myIntent.putExtra("ingredient", selectedItem)
            startActivity(myIntent)
        }
    }
}