package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvvm.clean.app.R
import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor

@Composable
fun HomeAppBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        navigationIcon = {
            Icon(
                Icons.Default.Menu,
                contentDescription = "",
                modifier = Modifier.padding(16.dp)
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    color = MaterialTheme.colors.AppContentColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        })
}
