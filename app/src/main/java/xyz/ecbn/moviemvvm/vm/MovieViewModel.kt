package xyz.ecbn.moviemvvm.vm

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.ServiceInterface
import xyz.ecbn.moviemvvm.data.model.GenreCollection
import xyz.ecbn.moviemvvm.data.model.MovieData

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieViewModel(private val serviceInterface: ServiceInterface) : ViewModel() {
    private val _uiState = MutableLiveData<MovieDataState>()
    val movieState: LiveData<MovieDataState> get() = _uiState

    private val _genreState = MutableLiveData<GenreDataState>()
    val genreState: LiveData<GenreDataState> get() = _genreState

    fun getGenres() {
        viewModelScope.launch {   // 4
            runCatching {  //  5
                serviceInterface.genreMovies()  // 6
            }.onSuccess {
                _genreState.value = GenreDataState(it.genres)
            }.onFailure {
                Log.d(TAG, "onFailure: ${it.message}")
            }
        }
    }

    fun getMovies(genre: String = "") {
        viewModelScope.launch {   // 4
            runCatching {  //  5
                emitUiState(showProgress = true)
                serviceInterface.popularMovies(withGenre = genre)  // 6
            }.onSuccess {
                emitUiState(movies = it.results)    // 7
            }.onFailure {
                emitUiState(error = R.string.internet_failure_error)   //  8
            }
        }
    }

    private fun emitUiState(
        showProgress: Boolean = false,
        movies: ArrayList<MovieData>? = null,
        error: Int? = null
    ) {
        val dataState = MovieDataState(showProgress, movies, error)
        _uiState.value = dataState
    }
}

data class MovieDataState(   // 9
    val showProgress: Boolean,
    val movies: ArrayList<MovieData>?,
    val error: Int?
)

data class GenreDataState(
    val genres: ArrayList<GenreCollection.Genre>
)