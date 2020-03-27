package xyz.ecbn.moviemvvm.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.ecbn.moviemvvm.data.NetworkState
import xyz.ecbn.moviemvvm.data.ServiceInterface
import xyz.ecbn.moviemvvm.data.Status
import xyz.ecbn.moviemvvm.data.repo.MovieRepository

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieViewModel(
    private val serviceInterface: ServiceInterface,
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val TAG = MovieViewModel::class.java.simpleName
    private val _networkState = MutableLiveData<NetworkState>()
    val network: LiveData<NetworkState>
        get() = _networkState

    init {
        getGenres()
        getMovies()
    }

    val movies = movieRepository.movies
    val genres = movieRepository.genres

    fun getGenres() {
        viewModelScope.launch {
            runCatching {
                _networkState.postValue(NetworkState.LOADING)
                movieRepository.getGenres()
            }.onSuccess {
                _networkState.postValue(NetworkState.LOADED)
            }.onFailure {
                _networkState.postValue(NetworkState(Status.FAILED, it.message.toString()))
            }
        }
    }

    fun getMovies(page: Int = 1, genre: String = "") {
        viewModelScope.launch {
            runCatching {
                Log.d(TAG, "getMovies runCatching:")
                _networkState.postValue(NetworkState.LOADING)
                movieRepository.getMovies(page, genre)
            }.onSuccess {
                Log.d(TAG, "getMovies onSuccess:")
                _networkState.postValue(NetworkState.LOADED)
            }.onFailure {
                _networkState.postValue(NetworkState(Status.FAILED, it.message.toString()))
                Log.d(TAG, "getMovies onFailure: ${it.message}")
            }
        }
    }
}