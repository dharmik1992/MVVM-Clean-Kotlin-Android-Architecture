package com.mvvm.clean.cache.repository

import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieCache
import javax.inject.Inject


class MovieCacheImp @Inject constructor() : MovieCache{
    override suspend fun getPopularMovies(): List<MovieEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun isCached(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setLastCacheTime(lastCache: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun isExpired(): Boolean {
        TODO("Not yet implemented")
    }
}