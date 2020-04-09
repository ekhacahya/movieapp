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
data class Video(
    @PrimaryKey
    var id: String,
    var movieId: Int? = 0,
    @SerializedName("iso_3166_1")
    var iso31661: String? = null,
    @SerializedName("iso_639_1")
    var iso6391: String? = null,
    var key: String? = null,
    var name: String? = null,
    var site: String? = null,
    var size: Int? = null,
    var type: String? = null
) : Parcelable