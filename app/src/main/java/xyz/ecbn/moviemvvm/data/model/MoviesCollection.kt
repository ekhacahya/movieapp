package xyz.ecbn.moviemvvm.data.model

import com.google.gson.annotations.SerializedName

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
data class MoviesCollection(
    @SerializedName("results")
    val results: ArrayList<Movie>
)