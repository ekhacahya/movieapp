package xyz.ecbn.moviemvvm.module

import io.github.inflationx.calligraphy3.CalligraphyConfig
import org.koin.dsl.module
import xyz.ecbn.moviemvvm.DEFAULT_FONT
import xyz.ecbn.moviemvvm.R

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
}