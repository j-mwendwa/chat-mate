package com.example.chatmate.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.chatmate.navigation.Routes

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun LoginScreen (
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authState by viewModel.authstate.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },

            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.login(email, password)
                } else {
                    Toast.makeText(
                        context,
                        "Email and password cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Don't have an account? Sign up",
            modifier = Modifier.clickable {
                navController.navigate(Routes.SignUp.routes)
            })

        when (val state = authState) {
            is AuthState.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Chat.routes) {
                        popUpTo(Routes.Login.routes) {
                            inclusive = true
                        }
                    }
                }
                viewModel.reset()

            }

            is AuthState.Failure -> {
                LaunchedEffect(Unit) {
                    Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        AuthState.Idle -> {
        }

            else -> {}
        }
    }
    }
