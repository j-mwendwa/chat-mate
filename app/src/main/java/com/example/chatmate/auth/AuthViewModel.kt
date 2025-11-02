package com.example.chatmate.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AuthState{
    object Idle: AuthState
    object Loading: AuthState
    object LoggedOut: AuthState
    data class Success(val message: String): AuthState
    data class Failure(val message: String): AuthState
    
}
 
class AuthViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _authstate = MutableStateFlow<AuthState>(AuthState.Idle)
    val authstate = _authstate.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authstate.value = AuthState.Loading
            try {
                auth.signInWithEmailAndPassword(email, password)
                _authstate.value = AuthState.Success("Logged in successfully")

            } catch (e: Exception) {
                _authstate.value = AuthState.Failure(e.message ?: "Unknown error")
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            _authstate.value = AuthState.Loading
            try {
                auth.createUserWithEmailAndPassword(email, password)
                _authstate.value = AuthState.Success("Signed up successfully")

            } catch (e: Exception) {
                _authstate.value = AuthState.Failure(e.message ?: "Unknown error")
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authstate.value = AuthState.Success("Logged out successfully")
    }

    fun reset() {
        _authstate.value = AuthState.Idle
    }
}