package xyz.ecbn.moviemvvm.data.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import xyz.ecbn.moviemvvm.MOVIE_TYPE

@Entity
data class Movie(
    var adult: Boolean? = false,
    @SerializedName("backdrop_path")
    var backdropPath: String? = "",
    var budget: Int? = 0,
    @Ignore
    var genres: List<Genre?>? = listOf(),
    var homepage: String? = "",
    @PrimaryKey
    var id: Int? = 0,
    @SerializedName("imdb_id")
    var imdbId: String? = "",
    @SerializedName("original_language")
    var originalLanguage: String? = "",
    @SerializedName("original_title")
    var originalTitle: String? = "",
    var overview: String? = "",
    var popularity: Double? = 0.0,
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @Ignore
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany?>? = listOf(),
    @SerializedName("release_date")
    var releaseDate: String? = "",
    var revenue: Int? = 0,
    var runtime: Int? = 0,
    var status: String? = "",
    var tagline: String? = "",
    var title: String? = "",
    var video: Boolean? = false,
    var type: String? = MOVIE_TYPE.GENERAL.toString(),
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int? = 0
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.createTypedArrayList(Genre.CREATOR),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.createTypedArrayList(ProductionCompany.CREATOR),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readValue(Int::class.java.classLoader) as Int?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(adult)
        writeString(backdropPath)
        writeValue(budget)
        writeTypedList(genres)
        writeString(homepage)
        writeValue(id)
        writeString(imdbId)
        writeString(originalLanguage)
        writeString(originalTitle)
        writeString(overview)
        writeValue(popularity)
        writeString(posterPath)
        writeTypedList(productionCompanies)
        writeString(releaseDate)
        writeValue(revenue)
        writeValue(runtime)
        writeString(status)
        writeString(tagline)
        writeString(title)
        writeValue(video)
        writeValue(type)
        writeValue(voteAverage)
        writeValue(voteCount)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}