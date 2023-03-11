package com.mvvm.clean.data.source

import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieCache
import com.mvvm.clean.data.repository.MovieDataSource
import javax.inject.Inject

class MovieCacheDataSource @Inject constructor(private val movieCache: MovieCache) :
    MovieDataSource {
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return movieCache.getPopularMovies()
    }

    override suspend fun isCached(): Boolean {
        return movieCache.isCached()
    }
}