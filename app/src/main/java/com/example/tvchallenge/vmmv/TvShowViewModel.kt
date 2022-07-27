package com.example.tvchallenge.vmmv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TvShowViewModel : ViewModel(){
    private val _tvShow = MutableLiveData<List<TvShow>>()
    val tvShow: LiveData<List<TvShow>>
        get() = _tvShow


    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    private val repository = TvShowRepository()

    fun getTvShows(query : String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getListTvShow(query)
            }.onSuccess { result ->
                if (!result.isNullOrEmpty()) {
                    _tvShow.value = result
                    _isError.value = false
                }
            }.onFailure {
                _isError.value = true
            }
        }
    }
}