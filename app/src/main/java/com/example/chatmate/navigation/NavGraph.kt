package com.example.chatmate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatmate.auth.LoginScreen
import com.example.chatmate.auth.SignUp
import com.example.chatmate.auth.Splashscreen
import com.example.chatmate.chat.Chatscreen


@Composable
fun RootNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.routes
    ) {
        composable(Routes.Splash.routes) {
            Splashscreen(navController = navController)
        }
        composable(Routes.Login.routes) {
            LoginScreen(navController = navController)
        }
        composable(Routes.SignUp.routes) {
            SignUp(navController = navController)
        }
        composable(Routes.Chat.routes) {
            Chatscreen(navController = navController)
        }

    }
}