package com.example.chatmate.navigation

sealed class Routes(val routes: String ){
    object Splash : Routes("splash_screen")
    object Login : Routes("Login_screen")
    object SignUp : Routes("signup_screen")
    object Chat : Routes("chat_screen")
}