package com.mbh.moviebrowser.api.response

import com.mbh.moviebrowser.data.NetworkResponseModel
import com.mbh.moviebrowser.domain.Genre

data class GenreResponse (
    val genres: List<Genre>,
): NetworkResponseModel