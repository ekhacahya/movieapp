package xyz.ecbn.moviemvvm.module

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */

val picassoModule = module {
    single {
        val downloader = okHttp3Downloader(get())
        picasso(
            androidContext(), downloader
        )
    }
}

private fun okHttp3Downloader(client: OkHttpClient) = OkHttp3Downloader(client)
private fun picasso(context: Context, downloader: OkHttp3Downloader) = Picasso.Builder(context)
    .downloader(downloader)
