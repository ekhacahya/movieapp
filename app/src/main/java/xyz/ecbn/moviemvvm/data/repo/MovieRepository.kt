package xyz.ecbn.moviemvvm.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.ecbn.moviemvvm.data.ServiceInterface
import xyz.ecbn.moviemvvm.data.local.LocalDB
import xyz.ecbn.moviemvvm.data.model.Genre
import xyz.ecbn.moviemvvm.data.model.Movie
import kotlin.coroutines.CoroutineContext

/**
 * MovieAppMVVM Created by ecbn on 26/03/20.
 */
class MovieRepository(
    private val database: LocalDB,
    private val serviceInterface: ServiceInterface
) : CoroutineScope {

    private val TAG = MovieRepository::class.java.simpleName
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    /*val movie: LiveData<DetailMovie> =
        Transformations.map(database.movieDao(), id -> database.movieDao().getMovie(id))*/
    val movies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao().getMovies()) { it }
    val genres: LiveData<List<Genre>> =
        Transformations.map(database.movieDao().getGenres()) { it }

    fun detail(id: Int): LiveData<Movie> {
        return Transformations.map(database.movieDao().getMovie(id)) {
            it
        }
    }

    suspend fun getGenres(): List<Genre>? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "getGenres is called")
            val playlist = serviceInterface.genreMovies().genres
            database.movieDao().setGenres(playlist)
            return@withContext playlist
        }
    }

    suspend fun getMovie(id: Int): Movie? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "getMovie($id) is called")
            val playlist = serviceInterface.getMovie(id)
            database.movieDao().setMovie(playlist)
            return@withContext playlist
        }
    }

    suspend fun getMovies(page: Int = 1, genre: String = ""): List<Movie>? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "getMovies is called")
            val playlist = serviceInterface.popularMovies(page, genre).results
            database.movieDao().setMovies(playlist)
            return@withContext playlist
        }
    }

}