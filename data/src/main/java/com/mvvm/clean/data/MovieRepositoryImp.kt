package com.mvvm.clean.data

import com.mvvm.clean.data.mapper.MovieMapper
import com.mvvm.clean.data.source.MovieDataSourceFactory
import com.mvvm.clean.domain.models.Movie
import com.mvvm.clean.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieDataSourceFactory: MovieDataSourceFactory,
    private val movieMapper: MovieMapper
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<List<Movie>> = flow {
        val isCached = false
        val movieList =
            movieDataSourceFactory.getDataStore(isCached).getPopularMovies().map { movieEntity ->
                movieMapper.mapFromEntity(movieEntity)
            }
        emit(movieList)
    }
}