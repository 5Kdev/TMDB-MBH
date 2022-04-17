package com.mbh.moviebrowser.api

import androidx.lifecycle.LiveData
import com.mbh.moviebrowser.api.response.*
import com.mbh.moviebrowser.data.Resource
import com.mbh.moviebrowser.domain.Detail
import retrofit2.HttpException
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface MovieService {

        @GET("3/trending/all/day")
        fun fetchTrendingMovies(): LiveData<TMDBResponse<TrendingListResponse>>

        @GET("3/genre/movie/list")
        fun fetchGenres(): LiveData<TMDBResponse<GenreResponse>>

        @GET("/3/movie/{id}")
        suspend fun fetchMovieDetail(@Path("id") id:Long): Detail

}