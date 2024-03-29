package com.mvvm.clean.data

import com.mvvm.clean.data.mapper.MovieDetailMapper
import com.mvvm.clean.data.mapper.MovieMapper
import com.mvvm.clean.data.source.MovieDataSourceFactory
import com.mvvm.clean.domain.models.Movie
import com.mvvm.clean.domain.models.MovieDetail
import com.mvvm.clean.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of MovieRepository interface of domain layer
 *
 * @property movieDataSourceFactory data source factory instance
 * @property movieMapper movie mapper instance
 * @property movieDetailMapper movie detail mapper instance
 */
class MovieRepositoryImp @Inject constructor(
    private val movieDataSourceFactory: MovieDataSourceFactory,
    private val movieMapper: MovieMapper,
    private val movieDetailMapper: MovieDetailMapper
) : MovieRepository {
    /**
     * Function that returns the popular movie list based on the data source factory
     *
     * @return flow object of movie list
     */
    override suspend fun getPopularMovies(): Flow<List<Movie>> = flow {
        val isCached = movieDataSourceFactory.getCachedDataSource().isCached()
        val movieList =
            movieDataSourceFactory.getDataStore(isCached).getPopularMovies().map { movieEntity ->
                movieMapper.mapFromEntity(movieEntity)
            }
        saveMoviesInCache(movieList)
        emit(movieList)
    }

    /**
     * Function to save movie list data into local cache
     *
     * @param popularMovieList movie list
     */
    override suspend fun saveMoviesInCache(popularMovieList: List<Movie>) {
        val movieEntities = popularMovieList.map { movie ->
            movieMapper.mapToEntity(movie)
        }
        movieDataSourceFactory.getCachedDataSource().saveMoviesInCache(movieEntities)
    }

    /**
     * Function that returns movie details based on movie id from remote data source
     *
     * @param movieId movie Id
     * @return flow object of movie detail
     */
    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetail> = flow {
        val movieDetails = movieDataSourceFactory.getRemoteDataSource().getMovieDetails(movieId)
        emit(movieDetailMapper.mapFromEntity(movieDetails))
    }
}