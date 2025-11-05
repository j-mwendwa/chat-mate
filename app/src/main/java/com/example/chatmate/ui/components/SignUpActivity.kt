package com.example.chatmate.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.chatmate.auth.SignUp

class SignUpActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   setContent {

       val navController = rememberNavController()
       SignUp(navController = navController)

   }

    }

}