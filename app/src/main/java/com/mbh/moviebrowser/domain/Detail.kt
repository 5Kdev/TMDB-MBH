package com.mbh.moviebrowser.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "MovieDetail")
data class Detail(
@SerializedName("adult")
@Expose
val adult: Boolean = false,

@SerializedName("backdrop_path")
@Expose
val backdropPath: String,

@SerializedName("belongs_to_collection")
@Expose
val belongsToCollection: Any? = null,

@SerializedName("budget")
@Expose
val budget: Int = 0,

@SerializedName("genres")
@Expose
val genres: List<Genre> = ArrayList(),

@SerializedName("homepage")
@Expose
val homepage: String? = null,

@SerializedName("id")
@Expose
val id: Int = 0,

@SerializedName("imdb_id")
@Expose
val imdbId: String? = null,

@SerializedName("original_language")
@Expose
val originalLanguage: String? = null,

@SerializedName("original_title")
@Expose
val originalTitle: String? = null,

@SerializedName("overview")
@Expose
val overview: String? = null,

@SerializedName("popularity")
@Expose
val popularity: Float = 0.0f,

@SerializedName("poster_path")
@Expose
val posterPath: String? = null,


@SerializedName("release_date")
@Expose
val releaseDate: String? = null,

@SerializedName("revenue")
@Expose
val revenue: Int = 0,

@SerializedName("runtime")
@Expose
val runtime: Int = 0,


@SerializedName("status")
@Expose
val status: String? = null,

@SerializedName("tagline")
@Expose
val tagline: String,

@SerializedName("title")
@Expose
val title: String,

@SerializedName("video")
@Expose
val video: Boolean = false,

@SerializedName("vote_average")
@Expose
val voteAverage: Float = 0.0f,

@SerializedName("vote_count")
@Expose
val voteCount: Int = 0
): Serializable
