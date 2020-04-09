package xyz.ecbn.moviemvvm.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * MovieAppMVVM Created by ecbn on 09/04/20.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Credits(
    var cast: ArrayList<Actor> = arrayListOf(),
    var crew: ArrayList<Crew> = arrayListOf()
) : Parcelable