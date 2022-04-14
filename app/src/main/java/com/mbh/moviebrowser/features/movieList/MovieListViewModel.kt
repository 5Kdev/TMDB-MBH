package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.data.repository.MovieRepository
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel(){

val movieListLiveData: LiveData<Resource<List<Movie>>>
val genresLiveData: LiveData<Resource<List<Genre>>>

    init {
        movieListLiveData = movieRepository.loadMovies()
        genresLiveData= movieRepository.loadGenres()
    }

    fun getMovieListValues() = movieListLiveData.value
    fun getGenreListValues() = genresLiveData.value
}