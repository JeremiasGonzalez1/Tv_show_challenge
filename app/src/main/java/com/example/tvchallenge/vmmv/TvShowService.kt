package com.example.tvchallenge.vmmv

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvShowService {
    private val retrofit = RetrofitNET.getRetrofit()

    suspend fun getListTvShow(query:String) : List<TvShow>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(ApiService::class.java).getListTvShow(query)
            response.body() ?: emptyList()
        }
    }
}