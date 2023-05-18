package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvm.clean.app.presentation.utils.ExceptionHandler
import com.mvvm.clean.app.presentation.viewModel.HomeViewModel
import com.mvvm.clean.app.presentation.viewModel.MovieUIStateModel

import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor
import com.mvvm.clean.domain.models.Movie

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
    val uiState = viewModel.popularMovieList.collectAsState().value
    val scaffoldState : ScaffoldState = rememberScaffoldState()

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
            HomeAppBar()
        },
        backgroundColor = Color.LightGray,
        contentColor = MaterialTheme.colors.AppContentColor,
        scaffoldState = scaffoldState
    ) { paddingValue ->
        when (uiState) {
            is MovieUIStateModel.Loading -> LoadingScreen()
            is MovieUIStateModel.Success -> MovieListScreen(allPopularMovies = uiState.data, paddingValue)
            is MovieUIStateModel.Error -> ErrorHandling(errorMessage = stringResource(id = ExceptionHandler.parse(uiState.error)), scaffoldState)
            else -> { throw AssertionError() }
        }
    }
}

@Composable
fun ErrorHandling(errorMessage: String, scaffoldState: ScaffoldState) {
    LaunchedEffect(Unit) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = errorMessage, duration = SnackbarDuration.Short
        )
    }
}

@Composable
fun LoadingScreen() {
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900))
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progressAnimationValue,
            modifier = Modifier
                .size(100.dp)
                .rotate(degrees = 45f),
            color = Color.Magenta,
            strokeWidth = 10.dp
        )
    }
}

@Composable
fun MovieListScreen(allPopularMovies: List<Movie>, contentPaddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(contentPaddingValues),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
    ) {
        items(allPopularMovies.size) { movieIndex ->
            MovieListItem(allPopularMovies[movieIndex])
        }
    }
}