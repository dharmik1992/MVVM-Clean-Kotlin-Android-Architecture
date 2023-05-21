package com.mvvm.clean.app.presentation.screen.movieDetail

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvm.clean.app.R
import com.mvvm.clean.app.presentation.screen.AppBar
import com.mvvm.clean.app.presentation.screen.LoadingScreen
import com.mvvm.clean.app.presentation.screen.ShowSnackBar
import com.mvvm.clean.app.presentation.utils.ExceptionHandler
import com.mvvm.clean.app.presentation.viewModel.MovieDetailUIStateModel
import com.mvvm.clean.app.presentation.viewModel.MovieDetailViewModel
import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor

/**
 * Composable function which shows popular movie details based on the current UI state
 *
 * @param movieDetailViewModel viewModel Instance
 * @param movieId movie ID
 * @param navController navController
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: Int,
    navController: NavController?
) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
    val uiState = movieDetailViewModel.movieDetail.collectAsState().value
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    LaunchedEffect(Unit) {
        movieDetailViewModel.getMovieDetail(movieId)
    }

    Scaffold(
        topBar = {
            AppBar(stringResource(id = R.string.app_detail_screen_title), Icons.Default.ArrowBack) {
                navController?.navigateUp()
            }
        }, backgroundColor = Color.LightGray, contentColor = MaterialTheme.colors.AppContentColor
    ) {
        when (uiState) {
            is MovieDetailUIStateModel.Loading -> LoadingScreen()
            is MovieDetailUIStateModel.Success -> MovieDetail(uiState.data)
            is MovieDetailUIStateModel.Error -> ShowSnackBar(
                message = stringResource(
                    id = ExceptionHandler.parse(
                        uiState.error
                    )
                ), scaffoldState
            )
        }
    }
}