package xyz.ecbn.moviemvvm.module

import org.koin.dsl.module
import xyz.ecbn.moviemvvm.data.repo.MovieRepository
import xyz.ecbn.moviemvvm.data.repo.PersonRepository

/**
 * Created by ecbn on 11/04/2020.
 */
val repositoryModule = module {
    single { MovieRepository(get(), get()) }
    single { PersonRepository(get(), get()) }
}