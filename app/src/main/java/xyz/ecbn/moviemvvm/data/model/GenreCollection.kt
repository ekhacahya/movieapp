package xyz.ecbn.moviemvvm.data.model


import android.os.Parcel
import android.os.Parcelable

data class GenreCollection(
    var genres: ArrayList<Genre> = arrayListOf()
) {
    data class Genre(
        var id: Int = 0,
        var name: String = ""
    ) : Parcelable {
        constructor(source: Parcel) : this(
            source.readInt(),
            source.readString().toString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
            writeInt(id)
            writeString(name)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<Genre> = object : Parcelable.Creator<Genre> {
                override fun createFromParcel(source: Parcel): Genre = Genre(source)
                override fun newArray(size: Int): Array<Genre?> = arrayOfNulls(size)
            }
        }
    }
}