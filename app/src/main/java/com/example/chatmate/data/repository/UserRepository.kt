package com.example.chatmate.data.repository

import com.example.chatmate.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore.collection("users")

    suspend fun createUser(user: User) {
        db.document(user.uid).set(user).await()

    }


}