package com.mvvm.clean.remote.models

import com.squareup.moshi.Json

data class MovieDataModel(
    val page: Int,
    @field:Json(name = "results")
    val movieModel: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)