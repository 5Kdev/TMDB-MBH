package com.mbh.moviebrowser.features.movieDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.mbh.moviebrowser.api.response.MovieDetailResponse
import com.mbh.moviebrowser.api.response.TMDBResponse
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.data.repository.MovieRepository
import com.mbh.moviebrowser.domain.Detail
import okio.IOException

class MovieDetailsViewModel constructor(
    private val movieId: Long,
    val movieRepository: MovieRepository
) : ViewModel(){


    fun loadDetail() = liveData {
        try {
            Log.d("LOAD", "loadDetail")

            val detailData = movieRepository.getMovieDetail(movieId)
            emit(detailData)
        } catch (e: IOException) {
            Log.d("loadDetail", e.toString())
        } catch (e: Exception) {
            Log.d("loadDetail", e.toString())
        }
    }

    class Factory(private val movieId: Long,private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(movieId,movieRepository) as T
        }

    }
}


