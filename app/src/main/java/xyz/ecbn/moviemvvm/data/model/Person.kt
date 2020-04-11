package xyz.ecbn.moviemvvm.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Person(
    var adult: Boolean? = null,
    var biography: String? = null,
    var birthday: String? = null,
    var deathday: String? = null,
    var gender: Int? = 0,
    var homepage: String? = null,
    @PrimaryKey
    var id: Int = 0,
    var imdb_id: String? = null,
    var known_for_department: String? = null,
    var name: String? = null,
    var place_of_birth: String? = null,
    var popularity: Double? = null,
    @Ignore
    var images: Images? = null,
    @Ignore
    var credits: Credits? = null,
    var profile_path: String? = null
) : Parcelable