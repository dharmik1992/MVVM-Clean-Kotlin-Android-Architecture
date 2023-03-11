package com.mvvm.clean.data.repository

import com.mvvm.clean.data.models.MovieEntity

interface MovieDataSource {
    //Remote and cache
    suspend fun getPopularMovies() : List<MovieEntity>

    //cache
    suspend fun isCached(): Boolean
}