package com.mvvm.clean.app.presentation.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home_screen")
    object MovieDetails : Screen("movie_details_screen/{movieId}") {
        fun passMovieId(movieId : Int) = "movie_details_screen/$movieId"
    }
}