package com.scarry.makakumbha.navigation

sealed class Screen (
    val route : String
){
    object LoginScreen : Screen("Login_Screen")
    object SignUpScreen: Screen("Sign_Up_Screen")
    object TermsAndConditionsScreen: Screen("Terms_And_Conditions_Screen")
    object AuthTestScreen: Screen("Auth_Test_Screen")
}