package com.mvvm.clean.data.source

import com.mvvm.clean.data.repository.MovieCache
import com.mvvm.clean.data.repository.MovieDataSource
import javax.inject.Inject

open class MovieDataSourceFactory @Inject constructor(
    private val movieCacheDataSource: MovieCacheDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieCache: MovieCache
) {

    open suspend fun getDataStore(isCached: Boolean): MovieDataSource {
        return if (isCached && !movieCache.isExpired()) {
            getCachedDataSource()
        } else {
            getRemoteDataSource()
        }
    }

     fun getCachedDataSource(): MovieDataSource {
        return movieCacheDataSource
    }

    private fun getRemoteDataSource(): MovieDataSource {
        return movieRemoteDataSource
    }
}