package com.moin.bingeprime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moin.bingeprime.ui.screens.DetailsScreen
import com.moin.bingeprime.ui.screens.HomeScreen
import com.moin.bingeprime.ui.theme.BingePrimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BingePrimeTheme { BingePrimeApp() }
        }
    }
}

@Composable
fun BingePrimeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details/{mediaId}") { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toIntOrNull() ?: 0
            DetailsScreen(navController, mediaId)
        }
    }
}