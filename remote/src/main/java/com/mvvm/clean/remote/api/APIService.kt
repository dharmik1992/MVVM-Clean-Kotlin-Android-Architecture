package com.mvvm.clean.remote.api

import com.mvvm.clean.remote.models.MovieDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int = 1
    ): MovieDataModel
}