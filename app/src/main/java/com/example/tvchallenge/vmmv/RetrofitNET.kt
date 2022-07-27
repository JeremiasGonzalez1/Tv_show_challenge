package com.example.tvchallenge.vmmv

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitNET {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(" https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}