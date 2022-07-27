package com.example.tvchallenge.vmmv

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search/shows?q=:")
suspend fun getListTvShow(@Query("query") query : String) : Response<List<TvShow>>
}