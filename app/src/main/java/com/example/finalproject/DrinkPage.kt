package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.*

class DrinkPage : AppCompatActivity() {

    private val ingredientsList = listOf(
        "7-Up","Absolut Citron","Ale","Amaretto","Angelica root","Apple brandy","Apple cider","Apple juice","Applejack","Apricot brandy","Berries","Bitters","Blackberry brandy","Blended whiskey","Bourbon","Brandy","Cantaloupe","Carbonated water","Champagne","Cherry brandy","Chocolate liqueur","Chocolate syrup","Chocolate","Cider","Cocoa powder","Coffee brandy","Coffee liqueur","Coffee","Cognac","Cranberries","Cranberry juice","Creme de Cacao","Creme der Cassis","Dark rum","Dry Vermouth","Dubonnet Rouge","Egg yolk","Egg","Espresso","Everclear","Firewater","Galliano","Gin","Ginger","Grape juice","Grapefruit juice","Grapes","Grenadine","Heavy cream","Irish cream","Irish whiskey","Johnnie Walker","Kahlua","Kiwi","Lager","Lemon juice","Lemon vodka","Lemon","Lemonade","Light rum","Lime juice","Lime","Mango","Midori melon liqueur","Milk","Orange bitters","Orange","Ouzo","Peach Vodka","Peach nectar","Peppermint schnapps","Pineapple juice","Pisco","Port","Red wine","Ricard","Rum","Sambuca","Scotch","Sherry","Sloe gin","Southern Comfort","Spiced rum","Sprite","Strawberries","Strawberry schnapps","Sugar syrup","Sugar","Sweet Vermouth","Tea","Tequila","Tomato juice","Triple sec","Vodka","Water","Watermelon","Whiskey","Yoghurt"
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