package com.example.tvchallenge.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tvchallenge.databinding.ItemTvShowBinding
import com.example.tvchallenge.vmmv.TvShow
import com.squareup.picasso.Picasso

class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTvShowBinding.bind(view)
    fun bind(item : TvShow, onClickListener:(TvShow)->Unit){
        binding.apply {
            Picasso.get().load(item.show.image.medium).into(imgTvShow)
            txtTitleTvShow.text = item.show.name
            txtDescriptionTvShow.text = item.show.summary
            itemView.setOnClickListener { onClickListener(item) }
        }
    }
}