package xyz.ecbn.moviemvvm.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * MovieAppMVVM Created by ecbn on 28/03/20.
 */
class ProductionCompany(
    var id: Int? = 0,
    @SerializedName("logo_path")
    var logoPath: String? = "",
    var name: String? = "",
    @SerializedName("origin_country")
    var originCountry: String? = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeString(logoPath)
        writeString(name)
        writeString(originCountry)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductionCompany> =
            object : Parcelable.Creator<ProductionCompany> {
                override fun createFromParcel(source: Parcel): ProductionCompany =
                    ProductionCompany(source)

                override fun newArray(size: Int): Array<ProductionCompany?> = arrayOfNulls(size)
            }
    }
}