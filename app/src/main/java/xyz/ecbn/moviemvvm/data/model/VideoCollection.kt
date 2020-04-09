package xyz.ecbn.moviemvvm.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class VideoCollection(
    var id: Int? = null,
    var results: ArrayList<Video> = arrayListOf()
) : Parcelable