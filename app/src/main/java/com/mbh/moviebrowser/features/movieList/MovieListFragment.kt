package com.mbh.moviebrowser.features.movieList

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.databinding.FragmentMovieListBinding
import com.mbh.moviebrowser.view.adapter.MovieListAdapter
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MovieListFragment : DaggerFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
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
        return binding<FragmentMovieListBinding>(inflater, R.layout.fragment_movie_list, container)
            .apply {
                viewModel = this@MovieListFragment.viewModel.apply { getMovieListValues() }
                lifecycleOwner = this@MovieListFragment
                adapter = MovieListAdapter(arrayListOf(),{ movie ->
                    (activity as MainActivity).selectedMovie= movie.id
                    Navigation.findNavController(requireView()).navigate(R.id.toMovieDetails);
                })
            }.root


    }



}
