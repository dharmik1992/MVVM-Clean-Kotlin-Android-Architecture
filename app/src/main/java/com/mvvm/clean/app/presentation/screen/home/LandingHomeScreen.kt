package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeAppBar()
        }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .background(Color.Black)
                .fillMaxSize()
        ) {
            Column {
                MovieListItem()
            }
        }
    }
}