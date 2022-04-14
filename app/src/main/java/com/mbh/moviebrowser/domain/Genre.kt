package com.mbh.moviebrowser.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey@SerializedName("id")val id: Long,
    @SerializedName("name")val name: String?,
): Serializable
