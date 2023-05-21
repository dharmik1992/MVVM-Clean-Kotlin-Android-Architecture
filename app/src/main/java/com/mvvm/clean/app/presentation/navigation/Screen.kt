package com.mvvm.clean.app.presentation.navigation

/**
 * Sealed class to store apps all screens route information that needs to be navigated
 *
 * @property route route key of each screen
 */
sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object MovieDetails : Screen("movie_details_screen/{movieId}") {
        fun passMovieId(movieId: Int) = "movie_details_screen/$movieId"
    }
}