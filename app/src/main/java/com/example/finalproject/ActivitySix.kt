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

                    // check if the ingredient or measure is null. There may be an ingredient but not a corresponding measure.
                    if (body.drinks[0].strIngredient1 != null) {
                        if (body.drinks[0].strMeasure1 != null) {
                            one.text =
                                "1. ${body.drinks[0].strIngredient1} - ${body.drinks[0].strMeasure1}"
                        } else {
                            one.text =
                                "1. ${body.drinks[0].strIngredient1}"
                        }

                    }

                    if (body.drinks[0].strIngredient2 != null) {
                        if (body.drinks[0].strMeasure2 != null) {
                            two.text =
                                "2. ${body.drinks[0].strIngredient2} - ${body.drinks[0].strMeasure2}"
                        } else {
                            two.text =
                                "2. ${body.drinks[0].strIngredient2}"
                        }
                    }

                    if (body.drinks[0].strIngredient3 != null) {
                        if (body.drinks[0].strMeasure3 != null) {
                            three.text =
                                "3. ${body.drinks[0].strIngredient3} - ${body.drinks[0].strMeasure3}"
                        } else {
                            three.text =
                                "3. ${body.drinks[0].strIngredient3}"
                        }
                    }

                    if (body.drinks[0].strIngredient4 != null) {
                        if (body.drinks[0].strMeasure4 != null) {
                            four.text =
                                "4. ${body.drinks[0].strIngredient4} - ${body.drinks[0].strMeasure4}"
                        } else {
                            four.text =
                                "4. ${body.drinks[0].strIngredient4}"
                        }
                    }

                    if (body.drinks[0].strIngredient5 != null) {
                        if (body.drinks[0].strMeasure5 != null) {
                            five.text =
                                "5. ${body.drinks[0].strIngredient5} - ${body.drinks[0].strMeasure5}"
                        } else {
                            five.text =
                                "5. ${body.drinks[0].strIngredient5}"
                        }
                    }

                    if (body.drinks[0].strIngredient6 != null) {
                        if (body.drinks[0].strMeasure6 != null) {
                            six.text =
                                "6. ${body.drinks[0].strIngredient6} - ${body.drinks[0].strMeasure6}"
                        } else {
                            six.text =
                                "6. ${body.drinks[0].strIngredient6}"
                        }
                    }

                    if (body.drinks[0].strIngredient7 != null) {
                        if (body.drinks[0].strMeasure7 != null) {
                            seven.text =
                                "7. ${body.drinks[0].strIngredient7} - ${body.drinks[0].strMeasure7}"
                        } else {
                            seven.text =
                                "7. ${body.drinks[0].strIngredient7}"
                        }
                    }

                    if (body.drinks[0].strIngredient8 != null) {
                        if (body.drinks[0].strMeasure8 != null) {
                            eight.text =
                                "8. ${body.drinks[0].strIngredient8} - ${body.drinks[0].strMeasure8}"
                        } else {
                            eight.text =
                                "8. ${body.drinks[0].strIngredient8}"
                        }
                    }

                    if (body.drinks[0].strIngredient9 != null) {
                        if (body.drinks[0].strMeasure9 != null) {
                            nine.text =
                                "9. ${body.drinks[0].strIngredient9} - ${body.drinks[0].strMeasure9}"
                        } else {
                            nine.text =
                                "9. ${body.drinks[0].strIngredient9}"
                        }
                    }

                    if (body.drinks[0].strIngredient10 != null) {
                        if (body.drinks[0].strMeasure10 != null) {
                            ten.text =
                                "10. ${body.drinks[0].strIngredient10} - ${body.drinks[0].strMeasure10}"
                        } else {
                            ten.text =
                                "10. ${body.drinks[0].strIngredient10}"
                        }
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