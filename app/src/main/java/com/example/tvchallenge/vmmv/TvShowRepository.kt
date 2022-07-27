package com.example.tvchallenge.vmmv

class TvShowRepository {
    private val service = TvShowService()

    suspend fun getListTvShow(query : String): List<TvShow>{
        return service.getListTvShow(query)
    }
}