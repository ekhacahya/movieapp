package xyz.ecbn.moviemvvm.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.ecbn.moviemvvm.vm.DetailMovieViewModel
import xyz.ecbn.moviemvvm.vm.MovieViewModel
import xyz.ecbn.moviemvvm.vm.PersonViewModel

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { PersonViewModel(get()) }
}