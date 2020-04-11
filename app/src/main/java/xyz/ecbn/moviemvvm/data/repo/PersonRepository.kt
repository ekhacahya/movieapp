package xyz.ecbn.moviemvvm.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.ecbn.moviemvvm.MOVIE_TYPE
import xyz.ecbn.moviemvvm.data.ServiceInterface
import xyz.ecbn.moviemvvm.data.local.LocalDB
import xyz.ecbn.moviemvvm.data.model.*
import kotlin.coroutines.CoroutineContext

/**
 * MovieAppMVVM Created by ecbn on 26/03/20.
 */
class PersonRepository(
    private val database: LocalDB,
    private val serviceInterface: ServiceInterface
) : CoroutineScope {

    private val TAG = PersonRepository::class.java.simpleName
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun person(id: Int): LiveData<Person> {
        return Transformations.map(database.movieDao().getPerson(id)) { it }
    }

    suspend fun getPerson(id: Int): Person? {
        return withContext(Dispatchers.IO) {
            val playlist = serviceInterface.getPerson(id)
            playlist.credits?.cast?.map {
                it.personId = id
                database.movieDao().setActor(it)
            }
            playlist.images?.posters?.map {
                it.personId = id
                it.type = ImageType.POSTER.name
                database.movieDao().setPoster(it)
            }

            database.movieDao().setPerson(playlist)
            return@withContext playlist
        }
    }
}