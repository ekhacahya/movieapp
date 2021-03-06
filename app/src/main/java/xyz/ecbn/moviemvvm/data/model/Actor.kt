package xyz.ecbn.moviemvvm.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(primaryKeys = arrayOf("id", "movieId", "personId"))
data class Actor(
    @SerializedName("cast_id")
    var castId: Int? = null,
    var movieId: Int= 0,
    var personId: Int= 0,
    var character: String? = null,
    @SerializedName("credit_id")
    var creditId: String? = null,
    var gender: Int? = null,
    var id: Int,
    var name: String? = null,
    var order: Int? = null,
    @SerializedName("profile_path")
    var profilePath: String? = null
) : Parcelable