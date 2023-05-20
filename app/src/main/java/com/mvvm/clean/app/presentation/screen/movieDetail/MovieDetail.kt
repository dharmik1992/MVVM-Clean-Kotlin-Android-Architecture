package com.mvvm.clean.app.presentation.screen.movieDetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.mvvm.clean.app.BuildConfig
import com.mvvm.clean.domain.models.MovieDetail
import java.lang.Float

@Composable
fun MovieDetail(movieDetail: MovieDetail) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieItemImage(movieDetail.backdrop_path)
        MovieItemDetails(movieDetail)
    }
}

@Composable
fun MovieItemImage(posterPath: String) {
    val painter = rememberAsyncImagePainter(BuildConfig.POSTER_URL + posterPath)
    val transition by animateFloatAsState(
        targetValue =
        if (painter.state is AsyncImagePainter.State.Success) 1f else 0f
    )
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()

    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .scale(.8f + (.2f * transition))
                .graphicsLayer { rotationX = (1f - transition) * 5f }
                .alpha(Float.min(1f, transition / .2f)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MovieItemDetails(movieDetail: MovieDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(6.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = movieDetail.original_title, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieDetail.overview,
            style = MaterialTheme.typography.body2,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieDetail.popularity.toString(),
            style = TextStyle(color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        )
    }
}