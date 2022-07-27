package com.example.tvchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvchallenge.R
import com.example.tvchallenge.vmmv.TvShow

class TvShowAdapter(
    private val tvShows: List<TvShow>,
    private val onClickListener: (TvShow) -> Unit
) : RecyclerView.Adapter<TvShowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TvShowViewHolder(layoutInflater.inflate((R.layout.item_tv_show), parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val item = tvShows[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int =tvShows.size
}