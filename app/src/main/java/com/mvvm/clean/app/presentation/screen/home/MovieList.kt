package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mvvm.clean.app.ui.theme.MVVMCleanKotlinAndroidArchitectureTheme

@Composable
fun MovieListItem() {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top, unbounded = true)
            .padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 8.dp),
        elevation = 5.dp,
        backgroundColor = Color.LightGray
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MovieItemImage()
            MovieItemDetails()
        }
    }
}

@Composable
fun MovieItemImage() {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(5.dp)
    ) {
        AsyncImage(
            model = "",
            contentDescription = "",
            modifier = Modifier
                .padding(4.dp)
                .width(120.dp)
                .wrapContentHeight(),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None,
        )
    }
}

@Composable
fun MovieItemDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "The Matrix",
            style = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "The Matrix is an American media franchise consisting of four feature films, beginning with The Matrix and continuing with three sequels, The Matrix Reloaded, The Matrix Revolutions, and The Matrix Resurrections.",
            style = TextStyle(color = Color.Black, fontSize = 16.sp),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "* 6.5",
            style = TextStyle(color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMCleanKotlinAndroidArchitectureTheme {
      MovieListItem()
    }
}
