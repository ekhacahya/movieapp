package xyz.ecbn.moviemvvm.utils

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.room.TypeConverter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xyz.ecbn.moviemvvm.data.model.Genre
import xyz.ecbn.moviemvvm.data.model.ProductionCompany
import java.lang.reflect.Type
import java.util.*


/**
 * MovieAppMVVM Created by ecbn on 29/03/20.
 */
class Converters {

    @TypeConverter
    fun stringToGenre(data: String?): List<Genre?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Genre?>?>() {}.type
        return Gson().fromJson<List<Genre?>>(data, listType)
    }

    @TypeConverter
    fun genreToString(someObjects: List<Genre?>?): String? {
        return Gson().toJson(someObjects)
    }

    @TypeConverter
    fun stringToCompany(data: String?): List<ProductionCompany?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ProductionCompany?>?>() {}.type
        return Gson().fromJson<List<ProductionCompany?>>(data, listType)
    }

    @TypeConverter
    fun companyToString(someObjects: List<ProductionCompany?>?): String? {
        return Gson().toJson(someObjects)
    }

    /*@TypeConverter
    fun stringToVideos(data: String?): VideoCollection? {
        if (data == null) {
            return VideoCollection()
        }
        val listType: Type = object : TypeToken<VideoCollection>() {}.type
        val obj = Gson().fromJson<VideoCollection>(data, listType)
        val obj1 = Gson().toJson(obj)
        val listResult: Type = object : TypeToken<List<Video>>() {}.type
        return Gson().fromJson(obj1, listResult)
    }

    @TypeConverter
    fun videosToString(someObjects: List<Video>?): String? {
        return Gson().toJson(someObjects)
    }
*/
}

fun View.setPaddingRight(value: Int) = setPadding(paddingLeft, paddingTop, value, paddingBottom)
fun View.setPaddingLeft(value: Int) = setPadding(value, paddingTop, paddingRight, paddingBottom)
fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun View.showSnackbar(msg: String, timeLength: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, timeLength).show()
}

fun View.show(isShow: Boolean) {
    if (isShow) this.visibility = VISIBLE
    else this.visibility = GONE
}

fun View.show() {
    this.visibility = VISIBLE
}

fun View.hide() {
    this.visibility = GONE
}


/**
 * Set an onclick listener
 */
@Suppress("UNCHECKED_CAST")
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }