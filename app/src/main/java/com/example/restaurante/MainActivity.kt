package com.example.restaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.restaurante.ui.theme.RestauranteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    PantallaPrincipal()
                }
            }
        }
    }
}





