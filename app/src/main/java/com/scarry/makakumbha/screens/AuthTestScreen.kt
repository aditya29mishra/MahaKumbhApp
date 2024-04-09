package com.scarry.makakumbha.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scarry.makakumbha.R
import com.scarry.makakumbha.components.HeadingTextComponent
import com.scarry.makakumbha.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.scarry.makakumbha.firebase.AuthClass

// Initialize Firebase Authentication instance
private val auth = FirebaseAuth.getInstance()
internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}


@Composable
fun AuthTestScreen(navController: NavController) {
    val context = LocalContext.current
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
            SendtoDatabaseButton(onClick = {


                AuthClass().sendtoDatabase(context,context.findActivity() ,"Raunak","Seth")
            })
            SignOutButton(onClick = {
                // Handle sign out logic here
                navController.navigate(Screen.LoginScreen.route)
                auth.signOut()
            })
        }
    }
}
@Composable
fun SendtoDatabaseButton(onClick:() -> Unit)
{
    Text(
        text = "Send to Database",
        modifier = with(Modifier) {
            padding(8.dp)
                .background(color = Color.Blue)
                .clickable(onClick = onClick)
                .padding(16.dp)
        },
        color = Color.White
    )
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