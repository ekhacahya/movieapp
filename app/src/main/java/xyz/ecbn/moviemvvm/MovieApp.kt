package xyz.ecbn.moviemvvm

import android.app.Application
import com.facebook.stetho.Stetho
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import xyz.ecbn.moviemvvm.module.*

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
            modules(
                listOf(
                    retrofitModule,
                    viewModelModule,
                    appModule,
                    glideModule,
                    repositoryModule
                )
            )
        }
        Stetho.initializeWithDefaults(this)
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