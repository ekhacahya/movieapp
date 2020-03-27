package xyz.ecbn.moviemvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.ecbn.moviemvvm.LOCAL_DB_NAME
import xyz.ecbn.moviemvvm.data.model.GenreCollection
import xyz.ecbn.moviemvvm.data.model.MovieData

/**
 * MovieAppMVVM Created by ecbn on 23/03/20.
 */
@Database(
    entities = [MovieData::class, GenreCollection.Genre::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: LocalDB? = null

        fun getDatabase(context: Context): LocalDB? {
            if (INSTANCE == null) {
                synchronized(LocalDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LocalDB::class.java, LOCAL_DB_NAME
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
