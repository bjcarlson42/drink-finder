package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_five.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityFive : AppCompatActivity() {
    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)

        list_drinks.adapter = adapter
        list_drinks.layoutManager = LinearLayoutManager(this)

        val selectedItem = intent.getStringExtra("ingredient")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val drink = retrofit.create(fetch::class.java)

        // call the api and search!
        if (selectedItem != null) {
            drink.fetchResults(selectedItem).enqueue(object : Callback<DrinkData> {
                override fun onResponse(call: Call<DrinkData>, response: Response<DrinkData>) {
                    val body = response.body() ?: return // return if body is null
                    Log.d("fail", response.toString())
                    Log.d("TAGI", body.toString())

                    drinks.addAll(body.drinks)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<DrinkData>, t: Throwable) {
                    // something went wrong. No need to handle this
                    Log.d("TAGI", "fail")
                    Log.d("fail", "$t")
                }
            })
        }
}

    // what holds that data
    val drinks = mutableListOf<Drink>()
    val adapter = DrinkAdapter(this, drinks)
}