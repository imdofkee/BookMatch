package com.dofkee.bookme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.dofkee.bookme.ui.theme.BookMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookMeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    HomeScreen(modifier = Modifier.padding(innerPadding))
//                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                    AppRoutes(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

