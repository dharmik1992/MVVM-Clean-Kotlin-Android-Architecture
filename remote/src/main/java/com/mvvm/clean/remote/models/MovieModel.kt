package com.mvvm.clean.remote.models

import com.squareup.moshi.Json

data class MovieModel(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    @field:Json(name = "id")
    val movieId: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)