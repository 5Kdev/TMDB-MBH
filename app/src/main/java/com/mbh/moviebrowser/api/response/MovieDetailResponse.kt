package com.mbh.moviebrowser.api.response

import com.mbh.moviebrowser.data.NetworkResponseModel
import com.mbh.moviebrowser.domain.Genre

data class MovieDetailResponse(
    val genres: List<Genre>,
    val homepage: String?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: String?,
): NetworkResponseModel{

    fun getPoster():String{
        return "https://image.tmdb.org/t/p/w342"+this.poster_path.toString()
    }

}