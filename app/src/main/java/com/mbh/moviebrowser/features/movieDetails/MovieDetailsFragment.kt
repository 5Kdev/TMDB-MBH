package com.mbh.moviebrowser.features.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dev.adnetworkm.CheckNetworkStatus
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.binding.bindRemoteImage
import com.mbh.moviebrowser.data.repository.MovieRepository
import com.mbh.moviebrowser.databinding.FragmentMovieDetailsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MovieDetailsFragment : DaggerFragment(){

    @Inject
    lateinit var movieRepository: MovieRepository
    private val viewModel by lazy {
        var movieId = (activity as MainActivity).selectedMovie
        Log.d("movieId","movieId: "+movieId)
        ViewModelProvider(this, MovieDetailsViewModel.Factory(movieId,movieRepository)).get(MovieDetailsViewModel::class.java)
    }

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding<FragmentMovieDetailsBinding>(inflater, R.layout.fragment_movie_details, container)
            .apply {
                var title:TextView = this.title
                var description: TextView = this.description
                var cover:ImageView = this.cover
                viewModel = this@MovieDetailsFragment.viewModel.apply {
                    CheckNetworkStatus.getNetworkLiveData(requireActivity().applicationContext).observe(viewLifecycleOwner, Observer { t ->
                        when (t) {
                            true -> {
                                viewModel!!.loadDetail().observe(viewLifecycleOwner, Observer { t ->
                                    title.text=t.title
                                    description.text=t.overview
                                    cover.bindRemoteImage("https://image.tmdb.org/t/p/original/"+t.posterPath)
                                })
                            }
                            false -> {
                                Toast.makeText(requireActivity().applicationContext, "No Network Connection", Toast.LENGTH_SHORT).show()
                            }
                            null -> {
                                // TODO: Handle the connection...
                            }
                        }
                    })
                }
                lifecycleOwner = viewLifecycleOwner
            }.root
    }

    fun loadDetailsData() {

    }

}
