package com.mvvm.clean.app.presentation.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.mvvm.clean.app.BuildConfig
import com.mvvm.clean.app.R
import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor
import java.lang.Float

/**
 * Composable function which shows app bar based on the data received
 *
 * @param title app bar title
 * @param icon app bar icon
 * @param onIconClick higher order function on icon click
 */
@Composable
fun AppBar(title: String, icon: ImageVector, onIconClick: () -> Unit) {
    TopAppBar(backgroundColor = MaterialTheme.colors.AppThemeColor, navigationIcon = {
        Icon(icon,
            contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .clickable { onIconClick.invoke() })
    }, title = {
        Text(
            text = title, style = TextStyle(
                color = MaterialTheme.colors.AppContentColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.fillMaxWidth()
        )
    })
}

/**
 * Composable function to show loading progress bar
 */
@Composable
fun LoadingScreen() {
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900))
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progressAnimationValue,
            modifier = Modifier
                .size(100.dp)
                .rotate(degrees = 45f),
            color = Color.Magenta,
            strokeWidth = 10.dp
        )
    }
}

/**
 * Composable function to show SnackBar based on the data received
 *
 * @param message message to be shown
 * @param scaffoldState scaffoldState
 */
@Composable
fun ShowSnackBar(message: String, scaffoldState: ScaffoldState) {
    LaunchedEffect(Unit) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = message, duration = SnackbarDuration.Short
        )
    }
}

/**
 * Composable function to show Text based on custom values received
 *
 * @param title title
 * @param color color
 * @param fontSize size in sp
 * @param spaceSize size in dp
 * @param fontWeight fontWeight
 */
@Composable
fun TextWithCustomStyle(
    title: String,
    color: Color,
    fontSize: TextUnit,
    spaceSize: Dp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = title, style = TextStyle(color = color, fontSize = fontSize, fontWeight = fontWeight)
    )
    Spacer(modifier = Modifier.height(spaceSize))
}

/**
 * Composable function to show Text based on material styles
 *
 * @param originalTitle title
 * @param style material style
 * @param maxLine max lines
 * @param overflow overflow
 * @param spaceSize size in dp
 */
@Composable
fun TextWithMaterialStyle(
    originalTitle: String, style: TextStyle, maxLine: Int, overflow: TextOverflow, spaceSize: Dp
) {
    Text(
        text = originalTitle, style = style, maxLines = maxLine, overflow = overflow
    )
    Spacer(modifier = Modifier.height(spaceSize))
}

/**
 * Composable function to show movie image based on orientation requirement
 *
 * @param imageUrl image path
 * @param isVerticalImage is Vertical image required
 */
@Composable
fun ImageWithAnimation(imageUrl: String, isVerticalImage: Boolean) {
    val painter = rememberAsyncImagePainter(BuildConfig.POSTER_URL + imageUrl)

    val transition by animateFloatAsState(
        targetValue = if (painter.state is AsyncImagePainter.State.Success) 1f else 0f
    )
    Card(
        elevation = 5.dp, modifier = if (isVerticalImage) {
            Modifier
                .padding(4.dp)
                .width(120.dp)
                .wrapContentHeight()

        } else {
            Modifier
                .height(350.dp)
                .fillMaxWidth()
        }
    ) {
        Image(painter = painter,
            contentDescription = "",
            modifier = Modifier
                .scale(.8f + (.2f * transition))
                .graphicsLayer { rotationX = (1f - transition) * 5f }
                .alpha(Float.min(1f, transition / .2f)),
            contentScale = ContentScale.Crop)
    }
}

/**
 * Composable function to show movie rating with its respective drawable icon
 *
 * @param rating movie rating
 */
@Composable
fun RatingComponent(rating: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_rate),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    end = 2.dp,
                )
        )
        Text(text = rating, style = MaterialTheme.typography.body2)
    }
}

/**
 * Composable function to display movie release date with its respective drawable icon
 *
 * @param releaseDate movie release date
 */
@Composable
fun ReleaseDateComponent(releaseDate: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_date_range_24),
            contentDescription = null,
            modifier = Modifier.padding(
                end = 2.dp,
            )
        )
        Text(text = releaseDate, style = MaterialTheme.typography.body2)
    }
}