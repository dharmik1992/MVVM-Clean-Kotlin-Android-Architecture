package com.mvvm.clean.data.repository

import com.mvvm.clean.data.models.MovieEntity

interface MovieCache {
    //Cache
    suspend fun getPopularMovies() : List<MovieEntity>
    suspend fun isCached(): Boolean
    suspend fun setLastCacheTime(lastCache: Long)
    suspend fun isExpired(): Boolean
}