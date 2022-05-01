package com.example.finalproject

import com.google.gson.annotations.SerializedName

data class DrinkData(
    val drinks: List<Drink>
)

data class Drink(
    @SerializedName("strDrink") val drink_name : String,
    @SerializedName("strDrinkThumb") val img : String,
){}

