package xyz.ecbn.moviemvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.ecbn.moviemvvm.data.model.Genre
import xyz.ecbn.moviemvvm.data.model.Movie

/**
 * MovieAppMVVM Created by ecbn on 23/03/20.
 */
@Dao
interface MovieDao {
    /*MovieList*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(movie: ArrayList<Movie>)

    @Query("SELECT * from movie ORDER BY id ASC")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * from movie where type = 'NOW_PLAYING' ORDER BY id ASC")
    fun getNowPlaying(): LiveData<List<Movie>>

    @Query("SELECT * from movie WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movie>

    /*Genre Movie*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenre(movie: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenres(movie: ArrayList<Genre>)

    @Query("SELECT * from genre ORDER BY id ASC")
    fun getGenres(): LiveData<List<Genre>>


}