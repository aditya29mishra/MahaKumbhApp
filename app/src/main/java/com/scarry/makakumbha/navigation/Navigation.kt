package com.scarry.makakumbha.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scarry.makakumbha.screens.AuthTestScreen
import com.scarry.makakumbha.screens.LoginScreen
import com.scarry.makakumbha.screens.SignUpScreen
import com.scarry.makakumbha.screens.TermsAndConditionsScreen

@Composable
fun  Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.TermsAndConditionsScreen.route) {
            TermsAndConditionsScreen(navController = navController)
        }
        composable(route = Screen.AuthTestScreen.route) {
            AuthTestScreen(navController = navController)
        }
    }
}