package com.mvvm.clean.app.di

import android.content.Context
import androidx.room.RoomDatabase
import com.mvvm.clean.cache.dao.CacheDao
import com.mvvm.clean.cache.database.LocalDatabase
import com.mvvm.clean.cache.repository.MovieCacheImp
import com.mvvm.clean.cache.utils.CacheSharedPreferenceHelper
import com.mvvm.clean.data.repository.MovieCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabase.getInstance(context = context)
    }

    @Provides
    @Singleton
    fun provideCacheDao(localDatabase: LocalDatabase) : CacheDao{
        return localDatabase.cacheDao()
    }

    @Provides
    @Singleton
    fun provideCacheSharedPreferenceHelper(@ApplicationContext context: Context) : CacheSharedPreferenceHelper{
        return CacheSharedPreferenceHelper(context)
    }

    @Provides
    @Singleton
    fun provideMovieCache(movieCacheImp: MovieCacheImp): MovieCache {
        return movieCacheImp
    }
}