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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //я хз что это но без него не работает
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookMeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppRoutes(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

