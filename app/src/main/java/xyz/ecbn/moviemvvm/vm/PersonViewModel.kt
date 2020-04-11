package xyz.ecbn.moviemvvm.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.ecbn.moviemvvm.data.NetworkState
import xyz.ecbn.moviemvvm.data.Status
import xyz.ecbn.moviemvvm.data.repo.PersonRepository

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class PersonViewModel(
    private val personRepository: PersonRepository
) : ViewModel() {

    private val TAG = PersonViewModel::class.java.simpleName
    private val _networkState = MutableLiveData<NetworkState>()
    val network: LiveData<NetworkState>
        get() = _networkState

    fun person(id: Int) = personRepository.person(id)

    fun getPerson(id: Int) {
        viewModelScope.launch {
            runCatching {
                _networkState.postValue(NetworkState.LOADING)
                personRepository.getPerson(id)
            }.onSuccess {
                _networkState.postValue(NetworkState.LOADED)
            }.onFailure {
                _networkState.postValue(NetworkState(Status.FAILED, it.message.toString()))
            }
        }
    }

}