package xyz.ecbn.moviemvvm.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.ecbn.moviemvvm.vm.MovieViewModel

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
val movieModule = module {
    viewModel {
        MovieViewModel(get())
    }
}