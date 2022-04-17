package com.mbh.moviebrowser.features.movieDetails

import android.accounts.NetworkErrorException
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.navigation.Navigation
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.api.response.MovieDetailResponse
import com.mbh.moviebrowser.api.response.TMDBResponse
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.data.repository.MovieRepository
import com.mbh.moviebrowser.domain.Detail
import okio.IOException
import retrofit2.HttpException

class MovieDetailsViewModel constructor(
    private val movieId: Long,
    val movieRepository: MovieRepository,
    val applicationContext: Activity
) : ViewModel(){

    fun loadDetail() = liveData {
        try {
            Log.d("LOAD", "loadDetail")

            val detailData = movieRepository.getMovieDetail(movieId)
            emit(detailData)

        } catch (e: IOException) {
            showDialog(e.toString())
        } catch (e: HttpException) {
            showDialog(e.toString())
        }
    }

    class Factory(private val movieId: Long,private val movieRepository: MovieRepository,private val applicationContext: Activity) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(movieId,movieRepository,applicationContext) as T
        }
    }

    fun showDialog(text: String) {
        val builder = AlertDialog.Builder(applicationContext)
        builder.setTitle("Warning")
        builder.setMessage(text)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("OK"){dialogInterface, which ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}


