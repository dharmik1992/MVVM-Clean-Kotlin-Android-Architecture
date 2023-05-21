package com.mvvm.clean.app.presentation.screen.movieDetail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvvm.clean.app.R
import com.mvvm.clean.app.presentation.screen.ImageWithAnimation
import com.mvvm.clean.app.presentation.screen.TextWithCustomStyle
import com.mvvm.clean.domain.models.MovieDetail

/**
 * Composable Function which shows movie details screen based on data received
 *
 * @param movieDetail movie data
 */
@Composable
fun MovieDetail(movieDetail: MovieDetail) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageWithAnimation(movieDetail.backdrop_path, false)
        MovieItemDetails(movieDetail)
    }
}

/**
 * Composable function which shows some movie data on movie details screen
 *
 * @param movieDetail movie details
 */
@Composable
fun MovieItemDetails(movieDetail: MovieDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(14.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TextWithCustomStyle(movieDetail.original_title, Color.Black, 24.sp, 2.dp, FontWeight.Bold)
        TextWithCustomStyle(movieDetail.tagline, Color.Black, 13.sp, 6.dp,FontWeight.Bold)
        TextWithCustomStyle(movieDetail.status + " - " + movieDetail.release_date, Color.Black, 14.sp, 14.dp,FontWeight.Bold)
        TextWithCustomStyle(movieDetail.overview, Color.Black, 16.sp, 10.dp)
        TextWithCustomStyle(stringResource(id = R.string.text_avg_rating) + movieDetail.vote_average, Color.Black, 14.sp, 4.dp, FontWeight.Bold)
    }
}