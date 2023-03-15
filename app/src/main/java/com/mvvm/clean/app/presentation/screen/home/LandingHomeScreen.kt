package com.mvvm.clean.app.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor
import com.mvvm.clean.domain.models.Movie

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
    val allPopularMovies = viewModel.popularMovieList.collectAsState()

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
        content = {
            MovieList(allPopularMovies)
        }
    )
}

@Composable
fun MovieList(allPopularMovies: State<List<Movie>>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
    ) {
        items(allPopularMovies.value.size) { movieIndex ->
            MovieListItem(allPopularMovies.value[movieIndex])
        }
    }
}