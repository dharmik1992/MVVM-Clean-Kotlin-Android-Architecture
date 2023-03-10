package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeAppBar() {
    TopAppBar(
        backgroundColor = Color.DarkGray,
        navigationIcon = {
            Icon(
                Icons.Default.Menu,
                contentDescription = "",
                modifier = Modifier.padding(16.dp)
            )
        },
        title = {
            Text(
                text = "IMDB Movie App",
                style = TextStyle(
                    color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        })
}
