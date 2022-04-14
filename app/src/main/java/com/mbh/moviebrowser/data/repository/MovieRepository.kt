package com.mbh.moviebrowser.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbh.moviebrowser.api.MovieService
import com.mbh.moviebrowser.api.response.*
import com.mbh.moviebrowser.data.NetworkBoundRepository
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.data.db.MovieDao
import com.mbh.moviebrowser.domain.Detail
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    val movieService: MovieService,
    val movieDao: MovieDao,
){

    fun loadMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundRepository<List<Movie>, TrendingListResponse>() {
            override fun saveFetchData(items: TrendingListResponse) {
                movieDao.insertMovieList(movies = items.results)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return movieDao.getMovieList()
            }

            override fun fetchService(): LiveData<TMDBResponse<TrendingListResponse>> {
                return movieService.fetchTrendingMovies()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed $message")
            }
        }.asLiveData()
    }

    fun loadGenres():LiveData<Resource<List<Genre>>> {
        return object : NetworkBoundRepository<List<Genre>, GenreResponse>() {
            override fun saveFetchData(items: GenreResponse) {
                movieDao.insertGenres(genres = items.genres)
            }

            override fun shouldFetch(data: List<Genre>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Genre>> {
                return movieDao.getGenres()
            }

            override fun fetchService(): LiveData<TMDBResponse<GenreResponse>> {
                return movieService.fetchGenres()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed $message")
            }

        }.asLiveData()
    }

    @ExperimentalCoroutinesApi
    suspend fun getMovieDetail(movieId: Long): Detail = movieService.fetchMovieDetail(movieId)



}