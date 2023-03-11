package com.mvvm.clean.domain.repository

import com.mvvm.clean.domain.models.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun getPopularMovies(): Flow<List<Movie>>
}