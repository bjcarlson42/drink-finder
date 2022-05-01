package com.example.finalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Fetch {
        // fetches the yelp api with the parameters we pass in
        @GET("filter.php?")
        fun fetchResults(
            @Query("i") i: String) : Call<DrinkData>
}