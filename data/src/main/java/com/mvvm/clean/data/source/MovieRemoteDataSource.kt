package com.mvvm.clean.data.source

import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieDataSource
import com.mvvm.clean.data.repository.MovieRemote
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieRemote: MovieRemote) :
    MovieDataSource {
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return movieRemote.getPopularMovies()
    }

    override suspend fun saveMoviesInCache(popularMovieList: List<MovieEntity>) {
        throw UnsupportedOperationException("Save movie is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(): Boolean {
        throw UnsupportedOperationException("Cache is not supported for RemoteDataSource.")
    }
}