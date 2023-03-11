package com.mvvm.clean.data.repository

import com.mvvm.clean.data.models.MovieEntity

interface MovieRemote {
    //Remote
    suspend fun getPopularMovies() : List<MovieEntity>
}