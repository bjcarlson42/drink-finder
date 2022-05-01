package com.example.finalproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchDetail {
    @GET("search.php?")
    fun fetchMe(
        @Query("s") s: String) : Call<DrinkDetailData>
}