package xyz.ecbn.moviemvvm.data.model

import alirezat775.lib.carouselview.CarouselModel
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * MovieAppMVVM Created by ecbn on 21/03/20.
 */
data class MovieCollection(
    @SerializedName("results")
    val results: ArrayList<MovieData>
)

data class MovieData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("vote_average")
    val voteAverage: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    var isFavorite: Boolean = false
) : CarouselModel(), Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString(),
        source.readString().toString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(posterPath)
        writeString(backdropPath)
        writeString(originalTitle)
        writeString(voteAverage)
        writeString(overview)
        writeString(releaseDate)
        writeInt((if (isFavorite) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieData> = object : Parcelable.Creator<MovieData> {
            override fun createFromParcel(source: Parcel): MovieData = MovieData(source)
            override fun newArray(size: Int): Array<MovieData?> = arrayOfNulls(size)
        }
    }
}