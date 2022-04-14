package com.mbh.moviebrowser.api.response

import com.mbh.moviebrowser.data.NetworkResponseModel
import com.mbh.moviebrowser.domain.Movie

data class TrendingListResponse (
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
): NetworkResponseModel