package xyz.ecbn.moviemvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.ecbn.moviemvvm.data.model.GenreCollection
import xyz.ecbn.moviemvvm.data.model.MovieData

/**
 * MovieAppMVVM Created by ecbn on 23/03/20.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovie(movie: MovieData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(movie: ArrayList<MovieData>)

    @Query("SELECT * from movie_table ORDER BY id ASC")
    fun getMovies(): LiveData<List<MovieData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenre(movie: GenreCollection.Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenres(movie: ArrayList<GenreCollection.Genre>)

    @Query("SELECT * from movie_table ORDER BY id ASC")
    fun getGenres(): LiveData<List<GenreCollection.Genre>>


}