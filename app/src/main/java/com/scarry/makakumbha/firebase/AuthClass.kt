package com.scarry.makakumbha.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthClass {

    private val auth = FirebaseAuth.getInstance()

    // Function to handle user login
    fun loginUser(email: String, password: String, callback: (FirebaseUser?, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    callback(user, null) // Login successful, pass user data to callback
                } else {
                    callback(null, task.exception?.message) // Login failed, pass error message to callback
                }
            }
    }

    // Function to handle user registration
    fun registerUser(email: String, password: String, callback: (FirebaseUser?, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    callback(user, null) // Registration successful, pass user data to callback
                } else {
                    callback(null, task.exception?.message) // Registration failed, pass error message to callback
                }
            }
    }


}