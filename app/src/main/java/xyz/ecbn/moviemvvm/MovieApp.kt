package xyz.ecbn.moviemvvm

import android.app.Application
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import xyz.ecbn.moviemvvm.module.appModule
import xyz.ecbn.moviemvvm.module.picassoModule
import xyz.ecbn.moviemvvm.module.retrofitModule
import xyz.ecbn.moviemvvm.module.viewModelModule

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
class MovieApp : Application() {
    private val calConfig: CalligraphyConfig by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MovieApp)
            modules(listOf(retrofitModule, picassoModule, viewModelModule, appModule))
        }

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        calConfig
                    )
                )
                .build()
        )
    }
}