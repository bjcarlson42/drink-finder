package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_six.*
import kotlinx.android.synthetic.main.drink.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivitySix : AppCompatActivity() {
    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six)

        val selectedDrink = intent.getStringExtra("drink")



        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val drink = retrofit.create(FetchDetail::class.java)

        // call the api and search!
        if (selectedDrink != null) {
            drink.fetchMe(selectedDrink).enqueue(object : Callback<DrinkDetailData> {
                override fun onResponse(call: Call<DrinkDetailData>, response: Response<DrinkDetailData>) {
                    val body = response.body() ?: return // return if body is null
                    Log.d("fail", response.toString())
                    Log.d("TAGI", body.toString())

                    name.text = body.drinks[0].drink_name
                    //use a placeholder image if there is none. Else, use the actual image
                    if (body.drinks[0].drink_image == null || body.drinks[0].drink_image.isEmpty()){
                        Glide.with(applicationContext)
                            .load("https://via.placeholder.com/150")
                            .into(image)
                    }
                    else {
                        Glide.with(applicationContext).load(body.drinks[0].drink_image).into(image)
                    }
                    if (body.drinks[0].strIngredient1 != null) {
                        one.text =
                            "${body.drinks[0].strIngredient1} - ${body.drinks[0].strMeasure1}"
                    }

                    if (body.drinks[0].strIngredient2 != null) {
                        two.text = "${body.drinks[0].strIngredient2} - ${body.drinks[0].strMeasure2}"
                    }

                    if (body.drinks[0].strIngredient3 != null) {
                        three.text = "${body.drinks[0].strIngredient3} - ${body.drinks[0].strMeasure3}"
                    }

                    if (body.drinks[0].strIngredient4 != null) {
                        four.text = "${body.drinks[0].strIngredient4} - ${body.drinks[0].strMeasure4}"
                    }

                    if (body.drinks[0].strIngredient5 != null) {
                        five.text = "${body.drinks[0].strIngredient5} - ${body.drinks[0].strMeasure5}"
                    }

                    if (body.drinks[0].strIngredient6 != null) {
                        six.text = "${body.drinks[0].strIngredient6} - ${body.drinks[0].strMeasure6}"
                    }

                    if (body.drinks[0].strIngredient7 != null) {
                        seven.text = "${body.drinks[0].strIngredient7} - ${body.drinks[0].strMeasure7}"
                    }

                    if (body.drinks[0].strIngredient8 != null) {
                        eight.text = "${body.drinks[0].strIngredient8} - ${body.drinks[0].strMeasure8}"
                    }

                    if (body.drinks[0].strIngredient9 != null) {
                        nine.text = "${body.drinks[0].strIngredient9} - ${body.drinks[0].strMeasure9}"
                    }

                    if (body.drinks[0].strIngredient10 != null) {
                        ten.text = "${body.drinks[0].strIngredient10} - ${body.drinks[0].strMeasure10}"
                    }
                }

                override fun onFailure(call: Call<DrinkDetailData>, t: Throwable) {
                    // something went wrong. No need to handle this
                    Log.d("TAGI", "fail")
                    Log.d("fail", "$t")
                }
            })
        }
    }
}