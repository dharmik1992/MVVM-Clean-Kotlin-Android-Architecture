package com.mvvm.clean.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.clean.cache.models.MovieCacheEntity

@Dao
interface CacheDao {

    @Query("SELECT * FROM movies")
    fun getPopularMovies(): List<MovieCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(vararg movie: MovieCacheEntity)
}