package xyz.ecbn.moviemvvm.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * MovieAppMVVM Created by ecbn on 09/04/20.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Images(
    var backdrops: ArrayList<Image> = arrayListOf(),
    var profiles: ArrayList<Image> = arrayListOf(),
    var posters: ArrayList<Image> = arrayListOf()
) : Parcelable