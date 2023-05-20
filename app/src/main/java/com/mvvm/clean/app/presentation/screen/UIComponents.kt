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
import com.mvvm.clean.app.ui.theme.AppContentColor
import com.mvvm.clean.app.ui.theme.AppThemeColor
import java.lang.Float

@Composable
fun AppBar(title: String, icon: ImageVector, onIconClick: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        navigationIcon = {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable{onIconClick.invoke()}
            )
        },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colors.AppContentColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        })
}

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
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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

@Composable
fun ShowSnackBar(errorMessage: String, scaffoldState: ScaffoldState) {
    LaunchedEffect(Unit) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = errorMessage, duration = SnackbarDuration.Short
        )
    }
}

@Composable
fun TextWithCustomStyle(
    title: String,
    color: Color,
    fontSize: TextUnit,
    spaceSize: Dp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = title,
        style = TextStyle(color = color, fontSize = fontSize, fontWeight = fontWeight)
    )
    Spacer(modifier = Modifier.height(spaceSize))
}

@Composable
fun TextWithMaterialStyle(
    originalTitle: String,
    style: TextStyle,
    maxLine: Int,
    overflow: TextOverflow,
    spaceSize: Dp
) {
    Text(
        text = originalTitle,
        style = style,
        maxLines = maxLine,
        overflow = overflow
    )
    Spacer(modifier = Modifier.height(spaceSize))
}

@Composable
fun ImageWithAnimation(imageUrl: String, isVerticalImage: Boolean) {
    val painter = rememberAsyncImagePainter(BuildConfig.POSTER_URL + imageUrl)

    val transition by animateFloatAsState(
        targetValue =
        if (painter.state is AsyncImagePainter.State.Success) 1f else 0f
    )
    Card(
        elevation = 5.dp,
        modifier = if (isVerticalImage) {
            Modifier
                .padding(4.dp)
                .width(120.dp)
                .wrapContentHeight()

        } else {
            Modifier
                .height(300.dp)
                .fillMaxWidth()
        }
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