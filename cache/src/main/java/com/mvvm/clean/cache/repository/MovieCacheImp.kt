package com.mvvm.clean.cache.repository

import com.mvvm.clean.cache.dao.CacheDao
import com.mvvm.clean.cache.mapper.MovieCacheMapper
import com.mvvm.clean.cache.utils.CacheSharedPreferenceHelper
import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieCache
import javax.inject.Inject


class MovieCacheImp @Inject constructor(
    private val cacheDao: CacheDao,
    private val movieCacheMapper: MovieCacheMapper,
    private val cacheSharedPreferenceHelper: CacheSharedPreferenceHelper
) : MovieCache {
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return cacheDao.getPopularMovies().map { cacheMovie ->
            movieCacheMapper.mapFromCached(cacheMovie)
        }
    }

    override suspend fun saveMoviesInCache(popularMovieList: List<MovieEntity>) {
        cacheDao.addMovie(*popularMovieList.map {
            movieCacheMapper.mapToCache(it)
        }.toTypedArray())
    }

    override suspend fun isCached(): Boolean {
        return cacheDao.getPopularMovies().isNotEmpty()
    }

    override suspend fun setLastCacheTime(lastCache: Long) {
        cacheSharedPreferenceHelper.lastCacheTime = lastCache
    }

    /**
     * Method to check cache expiration
     */
    override suspend fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return cacheSharedPreferenceHelper.lastCacheTime
    }

    companion object {
        /**
         * Expiration time set to 5 minutes
         */
        const val EXPIRATION_TIME = (60 * 5 * 1000).toLong()
    }
}