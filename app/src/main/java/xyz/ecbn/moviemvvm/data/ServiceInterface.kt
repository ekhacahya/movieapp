package xyz.ecbn.moviemvvm.data

import retrofit2.http.GET
import retrofit2.http.Query
import xyz.ecbn.moviemvvm.data.model.GenreCollection
import xyz.ecbn.moviemvvm.data.model.MovieCollection

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
interface ServiceInterface {
    @GET(value = "discover/movie")
    suspend fun popularMovies(
        @Query(value = "page") pageNumber: Int = 1,
        @Query(value = "with_genres") withGenre: String
    ): MovieCollection

    @GET("genre/movie/list")
    suspend fun genreMovies(
        @Query("language") language: String = "en-US"
    ): GenreCollection
}