package xyz.ecbn.moviemvvm.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.ecbn.moviemvvm.data.ServiceInterface
import xyz.ecbn.moviemvvm.data.local.LocalDB
import xyz.ecbn.moviemvvm.data.model.GenreCollection
import xyz.ecbn.moviemvvm.data.model.MovieData
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

    val movies: LiveData<List<MovieData>> = Transformations.map(database.movieDao().getMovies()) {
        it
    }
    val genres: LiveData<List<GenreCollection.Genre>> =
        Transformations.map(database.movieDao().getGenres()) {
            it
        }

    suspend fun getGenres(): List<GenreCollection.Genre>? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "refresh videos is called")
            val playlist = serviceInterface.genreMovies().genres
            database.movieDao().setGenres(playlist)
            return@withContext playlist
        }
    }

    suspend fun getMovies(page: Int = 1, genre: String = ""): List<MovieData>? {
        return withContext(Dispatchers.IO) {
            Log.d(TAG, "refresh videos is called")
            val playlist = serviceInterface.popularMovies(page, genre).results
            database.movieDao().setMovies(playlist)
            return@withContext playlist
        }
    }

}