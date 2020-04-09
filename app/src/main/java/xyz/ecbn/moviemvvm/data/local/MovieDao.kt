package xyz.ecbn.moviemvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.ecbn.moviemvvm.data.model.Actor
import xyz.ecbn.moviemvvm.data.model.Genre
import xyz.ecbn.moviemvvm.data.model.Movie
import xyz.ecbn.moviemvvm.data.model.Video

/**
 * MovieAppMVVM Created by ecbn on 23/03/20.
 */
@Dao
interface MovieDao {
    /*MovieList*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovie(vararg users: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(movie: ArrayList<Movie>)

    @Query("SELECT * from Movie ORDER BY id ASC")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * from Movie where type = 'NOW_PLAYING' ORDER BY id ASC")
    fun getNowPlaying(): LiveData<List<Movie>>

    @Query("SELECT * from Movie WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movie>

    @Query("SELECT * from Video WHERE movieId = :id")
    fun getVideos(id: Int): LiveData<List<Video>>

    @Query("SELECT * from Actor WHERE movieId = :id")
    fun getActors(id: Int): LiveData<List<Actor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setVideos(videos: ArrayList<Video>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setVideo(video: Video)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setActor(actor: Actor)

    /*Genre Movie*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenre(movie: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenres(movie: ArrayList<Genre>)

    @Query("SELECT * from Genre ORDER BY id ASC")
    fun getGenres(): LiveData<List<Genre>>


}