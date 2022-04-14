package com.mbh.moviebrowser.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mbh.moviebrowser.domain.Detail
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import kotlinx.coroutines.CoroutineExceptionHandler

@Database(entities = [Movie::class,Genre::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class MovieDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile private var INSTANCE: MovieDatabase? = null

        fun getInstance(app: Application): MovieDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(app).also { INSTANCE = it }
        }

        private fun buildDatabase(app: Application) =
            Room.databaseBuilder(app, MovieDatabase::class.java, "dbname")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val handler = CoroutineExceptionHandler { _, exception ->
                            println("Caught during database creation --> $exception")
                        }
                    }
                })
                .build()

    }
}