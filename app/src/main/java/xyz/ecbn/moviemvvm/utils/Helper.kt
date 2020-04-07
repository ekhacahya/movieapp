package xyz.ecbn.moviemvvm.utils

import android.view.View
import androidx.room.TypeConverter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xyz.ecbn.moviemvvm.MOVIE_TYPE
import xyz.ecbn.moviemvvm.data.model.*
import java.lang.reflect.Type
import java.util.*


/**
 * MovieAppMVVM Created by ecbn on 29/03/20.
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun typeToString(date: MOVIE_TYPE?): String? {
        return date?.name
    }

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
}

fun View.setPaddingRight(value: Int) = setPadding(paddingLeft, paddingTop, value, paddingBottom)
fun View.setPaddingLeft(value: Int) = setPadding(value, paddingTop, paddingRight, paddingBottom)
fun View.showSnackbar(msg: String, timeLength: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, timeLength).show()
}

/**
 * Set an onclick listener
 */
@Suppress("UNCHECKED_CAST")
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }