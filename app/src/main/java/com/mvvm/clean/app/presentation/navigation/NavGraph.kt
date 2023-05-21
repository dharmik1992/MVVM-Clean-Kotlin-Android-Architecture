package com.mvvm.clean.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mvvm.clean.app.presentation.screen.home.HomeScreen
import com.mvvm.clean.app.presentation.screen.movieDetail.MovieDetailScreen

/**
 * This function contains all the navigation related information like route screen, arguments to be shared etc.
 *
 * @param navController navController
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.MovieDetails.route, arguments = listOf(navArgument("movieId") {
            type = NavType.IntType
        })) { backStackEntry ->
            backStackEntry.arguments?.getInt("movieId")?.let { movieId ->
                MovieDetailScreen(
                    movieId = movieId, navController = navController
                )
            }
        }
    }
}