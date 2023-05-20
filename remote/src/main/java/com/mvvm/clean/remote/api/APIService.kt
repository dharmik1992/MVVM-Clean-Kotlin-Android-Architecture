package com.mvvm.clean.remote.api

import com.mvvm.clean.remote.models.movieDetailModels.MovieDetailModel
import com.mvvm.clean.remote.models.popularMovieModels.MovieDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieDataModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailModel
}