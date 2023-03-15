package com.mvvm.clean.app.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val BlueGrey900 = Color(0xFF263238)
val Grey100 = Color(0xFFF5F5F5)


val Colors.AppThemeColor
    get() = if (isLight) Color.White else Color.Black

val Colors.AppContentColor
    get() = if (isLight) Color.Black else Color.White

val Colors.TitleColor
    get() = if (isLight) Color.Black else Color.White

val Colors.ItemBackgroundColor get() = if (isLight) Grey100 else BlueGrey900