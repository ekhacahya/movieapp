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
data class Crew(
    @SerializedName("credit_id")
    var creditId: String? = null,
    var department: String? = null,
    var gender: Int? = null,
    @PrimaryKey
    var id: Int? = null,
    var job: String? = null,
    var name: String? = null,
    @SerializedName("profile_path")
    var profilePath: String? = null
) : Parcelable