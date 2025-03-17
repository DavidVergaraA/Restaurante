package com.example.restaurante

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipal() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo superior (debe estar en drawable)
        Image(
            painter = painterResource(id = R.drawable.logo), // Asegúrate de tener el logo en res/drawable
            contentDescription = "Logo Restaurante",
            modifier = Modifier
                .height(150.dp)
                .padding(bottom = 16.dp)
        )

        // Botón Menú Platos
        Button(
            onClick = {
                context.startActivity(Intent(context, MenuPlatosActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Menú platos")
        }

        // Botón Menú Bebidas
        Button(
            onClick = {
                context.startActivity(Intent(context, MenuBebidasActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Menú bebidas")
        }

        // Botón Ubicaciones
        Button(
            onClick = {
                context.startActivity(Intent(context, UbicacionesActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Ubicaciones")
        }

    }
}
