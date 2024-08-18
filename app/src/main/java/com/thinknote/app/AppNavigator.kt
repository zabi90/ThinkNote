package com.thinknote.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thinknote.app.AppNavigator.Routes.DETAILS
import com.thinknote.app.AppNavigator.Routes.HOME

import com.thinknote.app.ui.screens.detail.DetailScreen
import com.thinknote.app.ui.screens.home.HomeScreen

object AppNavigator {

    @Composable
    fun App(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HOME) {
            composable(HOME) { HomeScreen(navController, modifier) }
            composable(
                DETAILS,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType },
                    navArgument("categoryId") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailScreen(
                    navController, modifier, backStackEntry.arguments?.getInt("noteId"),
                    backStackEntry.arguments?.getInt("categoryId")
                )
            }
            // Add more destinations similarly.
        }
    }

    object Routes {
        const val HOME = "Home"
        const val DETAILS = "Details/{noteId}/{categoryId}"
    }

}