package xyz.ecbn.moviemvvm.module

import io.github.inflationx.calligraphy3.CalligraphyConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import xyz.ecbn.moviemvvm.DEFAULT_FONT
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.local.LocalDB
import xyz.ecbn.moviemvvm.utils.DarkModeHelper

/**
 * MovieAppMVVM Created by ecbn on 22/03/20.
 */
val appModule = module {
    single {
        CalligraphyConfig.Builder()
            .setDefaultFontPath(DEFAULT_FONT)
            .setFontAttrId(R.attr.fontPath)
            .build()
    }
    single { DarkModeHelper() }
    single {
        LocalDB.getDatabase(androidContext())
    }
}