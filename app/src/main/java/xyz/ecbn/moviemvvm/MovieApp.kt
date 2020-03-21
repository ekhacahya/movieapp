package xyz.ecbn.moviemvvm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import xyz.ecbn.moviemvvm.module.movieModule
import xyz.ecbn.moviemvvm.module.picassoModule
import xyz.ecbn.moviemvvm.module.retrofitModule

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)  // 2
            androidContext(this@MovieApp)  // 3
            modules(listOf(retrofitModule, picassoModule))  // 4
            modules(movieModule)
        }
    }
}