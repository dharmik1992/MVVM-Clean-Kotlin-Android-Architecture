package com.mvvm.clean.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.clean.cache.dao.CacheDao
import com.mvvm.clean.cache.models.Movie
import com.mvvm.clean.cache.utils.CacheConstants
import com.mvvm.clean.cache.utils.Migrations
import javax.inject.Inject

@Database(entities = [Movie::class], version = Migrations.DB_VERSION, exportSchema = false)
abstract class LocalDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cacheDao(): CacheDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}