package com.example.tvchallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tvchallenge.R
import com.example.tvchallenge.databinding.FragmentDetailTvShowBinding
import com.example.tvchallenge.vmmv.TvShow
import com.example.tvchallenge.vmmv.TvShowViewModel
import com.squareup.picasso.Picasso


class DetailTvShowFragment : Fragment() {
    companion object {
        const val INTENT_START_FRAGMENT_TV_SHOW = "intent_start_fragment_tv_show"
        const val INTENT_START_FRAGMENT_TV_SHOW_NAME = "intent_start_fragment_tv_show_name"
    }
    private val tvShowViewModel : TvShowViewModel by viewModels()
    private lateinit var binding: FragmentDetailTvShowBinding

    private var idTvShow = ""
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(TvShowSearchFragment.INTENT_START_FRAGMENT_TV_SHOW)?.let { idTvShow = it }
        arguments?.getString(TvShowSearchFragment.INTENT_START_FRAGMENT_TV_SHOW_NAME)?.let { query = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailTvShowBinding.inflate(inflater, container, false)
        tvShowViewModel.getTvShows(query)
        tvShowViewModel.tvShow.observe(viewLifecycleOwner){response->
            response?.let {
                for(item in response){
                    if (item.show.id.toString() == idTvShow){
                        setupView(item)
                    }
                }
            }
        }

        return binding.root
    }

    fun setupView(tvShow : TvShow){
        binding.apply {
            Picasso.get().load(tvShow.show.image.original).into(imgThumb)
            txtTitle.text = getString(R.string.txt_title, tvShow.show.name)
            txtLanguage.text = getString(R.string.txt_lang, tvShow.show.language)
            txtChamps.text = getString(R.string.txt_champ, tvShow.show.premiered)
            txtCountry.text = getString(R.string.txt_origin,tvShow.show.dvdCountry)
            txtType.text = getString(R.string.txt_type, tvShow.show.type)
            txtDescription.text = getString(R.string.txt_description, tvShow.show.summary)
            constraintDetail.visibility = View.VISIBLE
        }
    }

}