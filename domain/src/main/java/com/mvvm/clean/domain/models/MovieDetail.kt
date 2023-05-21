package com.mvvm.clean.domain.models

/***
 * data class of Movie Detail object
 */
data class MovieDetail(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
