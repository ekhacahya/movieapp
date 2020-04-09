package xyz.ecbn.moviemvvm.feature.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.ecbn.moviemvvm.data.NetworkState
import xyz.ecbn.moviemvvm.data.Status
import xyz.ecbn.moviemvvm.data.repo.MovieRepository

/**
 * MovieAppMVVM Created by ecbn on 30/03/20.
 */
class DetailMovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val TAG = DetailMovieViewModel::class.java.simpleName
    private val _networkState = MutableLiveData<NetworkState>()
    val network: LiveData<NetworkState>
        get() = _networkState

//    val mv = movieRepository.mv
    fun detail(id: Int) = movieRepository.detail(id)
    fun videos(id: Int) = movieRepository.videos(id)
    fun actors(id: Int) = movieRepository.actors(id)

    fun getMovie(id: Int) {
        viewModelScope.launch {
            runCatching {
                _networkState.postValue(NetworkState.LOADING)
                movieRepository.getMovie(id)
//                movieRepository.getVideos(id)
            }.onSuccess {
                _networkState.postValue(NetworkState.LOADED)
                Log.d(TAG, "onSuccess: $it")
            }.onFailure {
                _networkState.postValue(NetworkState(Status.FAILED, it.message.toString()))
            }
        }
    }
}