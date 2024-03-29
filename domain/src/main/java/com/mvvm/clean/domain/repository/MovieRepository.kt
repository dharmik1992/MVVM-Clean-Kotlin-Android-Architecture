package com.mvvm.clean.domain.repository

import com.mvvm.clean.domain.models.Movie
import com.mvvm.clean.domain.models.MovieDetail
import kotlinx.coroutines.flow.Flow

/***
 * Movie repository interface that needs to be implemented in data layer
 */
interface MovieRepository {
    //Remote and cache
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun saveMoviesInCache(popularMovieList: List<Movie>)

    //Remote
    suspend fun getMovieDetails(movieId : Int) : Flow<MovieDetail>
}