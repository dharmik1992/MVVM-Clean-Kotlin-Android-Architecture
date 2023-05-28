package com.mvvm.clean.remote.models.popularMovieModels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDataModel(
    val page: Int,
    @field:Json(name = "results")
    val movieModel: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)