package com.mvvm.clean.data.source

import com.mvvm.clean.data.models.MovieDetailEntity
import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieCache
import com.mvvm.clean.data.repository.MovieDataSource
import javax.inject.Inject

class MovieCacheDataSource @Inject constructor(private val movieCache: MovieCache) :
    MovieDataSource {
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return movieCache.getPopularMovies()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailEntity {
        throw UnsupportedOperationException("Operation is not supported for Cache")
    }

    override suspend fun saveMoviesInCache(popularMovieList: List<MovieEntity>) {
        movieCache.saveMoviesInCache(popularMovieList)
        movieCache.setLastCacheTime(System.currentTimeMillis())
    }

    override suspend fun isCached(): Boolean {
        return movieCache.isCached()
    }
}