package xyz.ecbn.moviemvvm.module

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import xyz.ecbn.moviemvvm.R

/**
 * MovieAppMVVM Created by ecbn on 27/03/20.
 */

val glideModule = module {
    single { provideRequestOptions() }
    single { provideRequestManager(androidApplication(), get()) }
}

fun provideRequestManager(
    application: Application,
    requestOptions: RequestOptions
): RequestManager {
    return Glide.with(application)
        .setDefaultRequestOptions(requestOptions)
}

fun provideRequestOptions(): RequestOptions {
    return RequestOptions()
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
}