package com.mvvm.clean.remote.repository

import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieRemote
import com.mvvm.clean.remote.api.APIService
import com.mvvm.clean.remote.mapper.MovieEntityMapper
import javax.inject.Inject

class MovieRemoteImp @Inject constructor(
    private val apiService: APIService,
    private val movieEntityMapper: MovieEntityMapper,
    private val apiKey: String
) : MovieRemote {
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return apiService.getPopularMovies(apiKey).movieModel.map { movieModel ->
            movieEntityMapper.mapFromModel(movieModel)
        }
    }
}