package com.scarry.makakumbha.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scarry.makakumbha.R
import com.scarry.makakumbha.components.HeadingTextComponent
import com.scarry.makakumbha.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

// Initialize Firebase Authentication instance
private val auth = FirebaseAuth.getInstance()

@Composable
fun AuthTestScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            HeadingTextComponent(value = stringResource(id = R.string.Test_Screen))
            Spacer(modifier = Modifier.height(16.dp))
            SignOutButton(onClick = {
                // Handle sign out logic here
                navController.navigate(Screen.LoginScreen.route)
                auth.signOut()
            })
        }
    }
}
@Composable
fun SignOutButton(onClick: () -> Unit) {
    Text(
        text = "Sign out",
        modifier = Modifier
            .padding(8.dp)
            .background(color = Color.Blue)
            .clickable(onClick = onClick)
            .padding(16.dp),
        color = Color.White
    )
}
@Preview
@Composable
fun AuthTestScreenPreview(){
    AuthTestScreen(navController = rememberNavController())
}