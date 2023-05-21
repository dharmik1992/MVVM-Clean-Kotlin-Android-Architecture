package com.mvvm.clean.remote.repository

import com.mvvm.clean.data.models.MovieDetailEntity
import com.mvvm.clean.data.models.MovieEntity
import com.mvvm.clean.data.repository.MovieRemote
import com.mvvm.clean.remote.api.APIService
import com.mvvm.clean.remote.mapper.MovieDetailEntityMapper
import com.mvvm.clean.remote.mapper.MovieEntityMapper
import javax.inject.Inject

/**
 * Implementation class of MovieRemote interface
 *
 * @property apiService retrofit service instance
 * @property movieEntityMapper movie mapper instance
 * @property movieDetailEntityMapper movie detail mapper instance
 * @property apiKey string api key
 */
class MovieRemoteImp @Inject constructor(
    private val apiService: APIService,
    private val movieEntityMapper: MovieEntityMapper,
    private val movieDetailEntityMapper: MovieDetailEntityMapper,
    private val apiKey: String
) : MovieRemote {

    /**
     * Function returns movie list from remote
     *
     * @return movies list
     */
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return apiService.getPopularMovies(apiKey).movieModel.map { movieModel ->
            movieEntityMapper.mapFromModel(movieModel)
        }
    }

    /**
     * Function returns movie details from remote based on movie id
     *
     * @param movieId movie id
     * @return movie details
     */
    override suspend fun getMovieDetails(movieId: Int): MovieDetailEntity {
        return movieDetailEntityMapper.mapFromModel(apiService.getMovieDetail(movieId, apiKey))
    }
}