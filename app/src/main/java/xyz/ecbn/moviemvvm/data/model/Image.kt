package xyz.ecbn.moviemvvm.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var movieId: Int? = null,
    var type: String? = ImageType.POSTER.name,
    @SerializedName("aspect_ratio")
    var aspectRatio: Double? = null,
    @SerializedName("file_path")
    var filePath: String? = null,
    var height: Int? = null,
    @SerializedName("iso_639_1")
    var iso6391: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int? = null,
    var width: Int? = null
) : Parcelable

enum class ImageType {
    POSTER,
    BACKDROP
}