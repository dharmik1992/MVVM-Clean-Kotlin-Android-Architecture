package com.mvvm.clean.app.presentation.screen.reusableComposables

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ShowSnackBar(errorMessage: String, scaffoldState: ScaffoldState) {
    LaunchedEffect(Unit) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = errorMessage, duration = SnackbarDuration.Short
        )
    }
}