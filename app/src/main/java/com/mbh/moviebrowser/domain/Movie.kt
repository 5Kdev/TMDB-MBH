package com.mbh.moviebrowser.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey@SerializedName("id")val id: Long,
    @SerializedName("title")val title: String?,
    @SerializedName( "original_title")val original_title: String?,
    @SerializedName("original_language")val original_language: String?,
    @SerializedName("overview")val overview: String,
    @SerializedName("poster_path")val coverUrl: String,
    @SerializedName("backdrop_path")val backdrop_path: String,
    @SerializedName("rating")val rating: Float,
    @SerializedName("popularity")val popularity: Float,
    @SerializedName("vote_count")val vote_count: Int,
    @SerializedName("vote_average")val vote_average: Float,
    @SerializedName("genre_ids")val genreIds: List<Int>,
): Serializable
