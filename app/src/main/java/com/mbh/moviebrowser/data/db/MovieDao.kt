package com.mbh.moviebrowser.data.db
import androidx.lifecycle.LiveData
import androidx.room.*
import com.mbh.moviebrowser.api.response.MovieDetailResponse
import com.mbh.moviebrowser.domain.Detail
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<Genre>)

    @Update
    fun updateMovie(movie: Movie)

    @Query("SELECT * FROM MOVIE WHERE id = :id_")
    fun getMovie(id_: Int): Movie

    @Query("SELECT * FROM Movie")
    fun getMovieList(): LiveData<List<Movie>>

    @Query("SELECT * FROM Genre")
    fun getGenres(): LiveData<List<Genre>>

}