package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvm.clean.app.R
import com.mvvm.clean.app.presentation.screen.AppBar
import com.mvvm.clean.app.presentation.screen.LoadingScreen
import com.mvvm.clean.app.presentation.screen.ShowSnackBar
import com.mvvm.clean.app.presentation.utils.ExceptionHandler
import com.mvvm.clean.app.presentation.viewModel.HomeViewModel
import com.mvvm.clean.app.presentation.viewModel.MovieUIStateModel
import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor

/**
 * Composable function which shows popular movie list based on the current UI state
 *
 * @param viewModel viewModel instance
 * @param navController navController
 */
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
    val uiState = viewModel.popularMovieList.collectAsStateWithLifecycle().value
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getPopularMovies()
    }

    Scaffold(
        topBar = {
            AppBar(
                stringResource(id = R.string.app_name), Icons.Default.Menu
            ) {}
        },
        backgroundColor = Color.LightGray,
        contentColor = MaterialTheme.colors.AppContentColor,
        scaffoldState = scaffoldState
    ) { paddingValue ->
        when (uiState) {
            is MovieUIStateModel.Loading -> LoadingScreen()
            is MovieUIStateModel.Success -> MovieListScreen(
                allPopularMovies = uiState.data, paddingValue, navController
            )
            is MovieUIStateModel.Error -> ShowSnackBar(
                message = stringResource(
                    id = ExceptionHandler.parse(
                        uiState.error
                    )
                ), scaffoldState
            )
        }
    }
}