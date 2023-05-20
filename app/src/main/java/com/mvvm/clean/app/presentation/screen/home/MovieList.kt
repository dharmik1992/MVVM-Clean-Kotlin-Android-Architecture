package com.mvvm.clean.app.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mvvm.clean.app.R
import com.mvvm.clean.app.presentation.navigation.Screen
import com.mvvm.clean.app.presentation.screen.ImageWithAnimation
import com.mvvm.clean.app.presentation.screen.TextWithCustomStyle
import com.mvvm.clean.app.presentation.screen.TextWithMaterialStyle
import com.mvvm.clean.app.ui.theme.ItemBackgroundColor
import com.mvvm.clean.domain.models.Movie

@Composable
fun MovieListScreen(
    allPopularMovies: List<Movie>,
    contentPaddingValues: PaddingValues,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.padding(contentPaddingValues),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
    ) {
        items(allPopularMovies.size) { movieIndex ->
            MovieListItem(allPopularMovies[movieIndex]) {
                navController.navigate(Screen.MovieDetails.passMovieId(allPopularMovies[movieIndex].movieId))
            }
        }
    }
}

@Composable
fun MovieListItem(movie: Movie?, onPopularMovieItemClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .height(175.dp)
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
            .clickable { onPopularMovieItemClick.invoke() },
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
                ImageWithAnimation(movie.poster_path,true)
                MovieItemDetails(movie)
            }
        }
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
        TextWithMaterialStyle(movie.original_title, MaterialTheme.typography.body1, 1, TextOverflow.Ellipsis, 4.dp)
        TextWithMaterialStyle(movie.overview, MaterialTheme.typography.body2, 4, TextOverflow.Ellipsis, 10.dp)
        TextWithCustomStyle(stringResource(id = R.string.text_imdb_rating) + movie.vote_average, Color.Black, 12.sp, 2.dp, FontWeight.Bold)
    }
}
