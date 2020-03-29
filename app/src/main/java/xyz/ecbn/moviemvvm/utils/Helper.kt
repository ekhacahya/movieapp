package xyz.ecbn.moviemvvm.utils

import android.view.View
import androidx.room.TypeConverter
import com.google.android.material.snackbar.Snackbar
import xyz.ecbn.moviemvvm.MOVIE_TYPE
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