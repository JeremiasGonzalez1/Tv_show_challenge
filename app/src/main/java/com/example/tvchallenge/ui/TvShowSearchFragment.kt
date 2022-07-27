package com.example.tvchallenge.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.tvchallenge.R
import com.example.tvchallenge.databinding.FragmentTvShowSearchBinding
import com.example.tvchallenge.vmmv.TvShow
import com.example.tvchallenge.vmmv.TvShowViewModel

class TvShowSearchFragment : Fragment(), SearchView.OnQueryTextListener{
    private lateinit var binding : FragmentTvShowSearchBinding
    private val viewModel : TvShowViewModel by viewModels()
    private var queryFrag : String = ""

    companion object{
        const val INTENT_START_FRAGMENT_TV_SHOW = "intent_start_fragment_tv_show"
        const val INTENT_START_FRAGMENT_TV_SHOW_NAME = "intent_start_fragment_tv_show_name"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowSearchBinding.inflate(layoutInflater)
        viewModel.tvShow.observe(viewLifecycleOwner){
            it?.let { binding.rvTvShow.adapter = TvShowAdapter(it) { itemSelected(it)}}
        }

        viewModel.isError.observe(viewLifecycleOwner){
            if(it != false)
             Toast.makeText( context,"Sucedio un error", Toast.LENGTH_SHORT).show()
        }

        binding.svTvShow.setOnQueryTextListener(this)

        return binding.root
    }

    private fun itemSelected(tvShow : TvShow){
        val bundle = Bundle()
        bundle.putString(DetailTvShowFragment.INTENT_START_FRAGMENT_TV_SHOW, tvShow.show.id.toString())
        bundle.putSerializable(DetailTvShowFragment.INTENT_START_FRAGMENT_TV_SHOW_NAME, queryFrag)
        NavHostFragment.findNavController(this).navigate(R.id.action_tvShowSearchFragment_to_detailTvShowFragment, bundle)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            viewModel.getTvShows(query)
            queryFrag = query
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

}