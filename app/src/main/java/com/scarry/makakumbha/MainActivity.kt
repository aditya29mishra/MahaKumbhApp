package com.scarry.makakumbha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import com.scarry.makakumbha.navigation.Navigation
import com.scarry.makakumbha.ui.theme.MakaKumbhaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakaKumbhaTheme {
                MakaKumbhaTheme {
                    FirebaseApp.initializeApp(this)
                    Navigation()
                }
            }
        }
    }
}
