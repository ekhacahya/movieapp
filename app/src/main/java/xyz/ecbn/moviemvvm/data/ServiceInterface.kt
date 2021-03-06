package xyz.ecbn.moviemvvm.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.ecbn.moviemvvm.data.model.*

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
interface ServiceInterface {
    @GET(value = "discover/movie")
    suspend fun popularMovies(
        @Query(value = "page") pageNumber: Int = 1,
        @Query(value = "with_genres") withGenre: String
    ): MoviesCollection

    @GET(value = "movie/now_playing")
    suspend fun nowPlaying(
        @Query(value = "page") pageNumber: Int = 1,
        @Query(value = "with_genres") withGenre: String
    ): MoviesCollection

    @GET("genre/movie/list")
    suspend fun genreMovies(
        @Query("language") language: String = "en-US"
    ): GenreCollection

    @GET("person/{id}")
    suspend fun getPerson(
        @Path("id") id: Int
    ): Person

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int
    ): Movie
}