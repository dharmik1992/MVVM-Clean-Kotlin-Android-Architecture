package com.mvvm.clean.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mvvm.clean.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.MOVIES_TABLE_NAME)
data class MovieCacheEntity(
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey
    val movieId: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)