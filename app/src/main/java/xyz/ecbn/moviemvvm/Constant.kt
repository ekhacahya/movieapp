package xyz.ecbn.moviemvvm

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
const val DEFAULT_FONT = "fonts/Roboto-Regular.ttf"
const val LOCAL_DB_NAME = "movie_database"

enum class MOVIE_TYPE {
    UPCOMING,
    TRENDING,
    GENERAL,
    RELEASE_NOW,
    NOW_PLAYING,
}