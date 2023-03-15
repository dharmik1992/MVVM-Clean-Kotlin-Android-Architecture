package com.mvvm.clean.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.clean.app.presentation.navigation.NavGraph
import com.mvvm.clean.app.ui.theme.MVVMCleanKotlinAndroidArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMCleanKotlinAndroidArchitectureTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

