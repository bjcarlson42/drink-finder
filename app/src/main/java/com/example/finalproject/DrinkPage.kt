package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.*

class DrinkPage : AppCompatActivity() {

    private val ingredientsList = listOf(
        "Light rum",
        "Applejack",
        "Gin",
        "Dark rum",
        "Sweet Vermouth",
        "Strawberry schnapps",
        "Scotch",
        "Apricot brandy",
        "Triple sec",
        "Southern Comfort",
        "Orange bitters",
        "Brandy",
        "Lemon vodka",
    )

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