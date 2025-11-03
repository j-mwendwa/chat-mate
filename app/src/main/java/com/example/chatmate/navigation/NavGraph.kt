package com.example.chatmate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatmate.auth.LoginScreen
import com.example.chatmate.auth.SignUp
import com.example.chatmate.auth.splash_screen


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
            LoginScreen(navController = navController)
        }
        composable(Routes.SignUp.routes) {
            SignUp(navController = navController)
        }
        composable(Routes.Chat.routes) {
            chat_screen(navController = navController)
        }

    }
}