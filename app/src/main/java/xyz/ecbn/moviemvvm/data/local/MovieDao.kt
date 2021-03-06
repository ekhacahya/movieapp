package xyz.ecbn.moviemvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.ecbn.moviemvvm.data.model.*

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

    @Query("SELECT * from Image WHERE movieId = :id and type = 'POSTER'")
    fun getPosters(id: Int): LiveData<List<Image>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setVideos(videos: ArrayList<Video>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setVideo(video: Video)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setActor(actor: Actor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setPoster(image: Image)

    /*Genre Movie*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenre(movie: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenres(movie: ArrayList<Genre>)

    @Query("SELECT * from Genre ORDER BY id ASC")
    fun getGenres(): LiveData<List<Genre>>

    /*Person*/
    @Query("SELECT * from Person WHERE id = :id")
    fun getPerson(id: Int): LiveData<Person>

    @Query("SELECT * from Actor WHERE personId = :id")
    fun getPersonActors(id: Int): LiveData<List<Actor>>

    @Query("SELECT * from Image WHERE personId = :id and type = 'POSTER'")
    fun getPersonPosters(id: Int): LiveData<List<Image>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setPerson(movie: Person)
}