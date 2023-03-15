package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mvvm.clean.app.BuildConfig
import com.mvvm.clean.app.ui.theme.ItemBackgroundColor
import com.mvvm.clean.app.ui.theme.MVVMCleanKotlinAndroidArchitectureTheme
import com.mvvm.clean.domain.models.Movie

@Composable
fun MovieListItem(movie: Movie?) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .height(175.dp)
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp),
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.ItemBackgroundColor
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
        ) {
            movie?.let {
                MovieItemImage(movie.poster_path)
                MovieItemDetails(movie)
            }
        }
    }
}

@Composable
fun MovieItemImage(posterPath: String) {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = BuildConfig.POSTER_URL + posterPath,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None,
        )
    }
}

@Composable
fun MovieItemDetails(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(6.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = movie.original_title,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.body2,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.vote_average.toString(),
            style = TextStyle(color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMCleanKotlinAndroidArchitectureTheme {
        MovieListItem(null)
    }
}
