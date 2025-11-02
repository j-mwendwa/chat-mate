package com.example.chatmate.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.chatmate.navigation.Routes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun splash_screen(navController: NavController){
    LaunchedEffect(key1 = true) {
        delay(2000)

        if (FirebaseAuth.getInstance().currentUser != null){
            navController.navigate(Routes.Chat.routes){
                popUpTo(Routes.Splash.routes){ // So popUpTo basically removes Splash Screen from the backstack
                    inclusive = true
                }
            }
        }else{
            navController.navigate(Routes.Login.routes){
                popUpTo(Routes.Splash.routes){ // So popUpTo basically removes Splash Screen from the backstack
                    inclusive = true
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "ChatMate") // Later we will add a logo here
        }
    }
}
