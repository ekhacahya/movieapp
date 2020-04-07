package xyz.ecbn.moviemvvm.data.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class ProductionCompany(
    @PrimaryKey
    var id: Int? = null,
    @SerializedName("logo_path")
    var logoPath: String? = null,
    var name: String? = null,
    @SerializedName("origin_country")
    var originCountry: String? = null
) : Parcelable