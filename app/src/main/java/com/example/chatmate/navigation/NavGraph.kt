package com.example.chatmate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatmate.splash_screen


@Composable
fun NavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.routes
    ) {
        composable(Routes.Splash.routes) {
            splash_screen(navController = navController)
        }
        composable(Routes.Login.routes) {
            Login_screen(navController = navController)
        }
        composable(Routes.SignUp.routes) {
            signup_screen(navController = navController)
        }
        composable(Routes.Chat.routes) {
            chat_screen(navController = navController)
        }

    }
}