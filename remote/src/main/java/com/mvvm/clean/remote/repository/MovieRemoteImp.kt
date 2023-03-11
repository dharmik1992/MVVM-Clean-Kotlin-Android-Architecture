package com.mvvm.clean.remote.repository

import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieRemote
import com.mvvm.clean.remote.api.APIService
import com.mvvm.clean.remote.mapper.MovieEntityMapper
import javax.inject.Inject

class MovieRemoteImp @Inject constructor(
    private val apiService: APIService, private val movieEntityMapper: MovieEntityMapper
) : MovieRemote {
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return apiService.getPopularMovies("3191b5978959810071d6369865507c05").movieModel.map { movieModel ->
            movieEntityMapper.mapFromModel(movieModel)
        }
    }
}