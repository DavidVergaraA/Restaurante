package com.example.restaurante

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurante.ui.theme.RestauranteTheme

class MenuBebidasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteTheme {
                PantallaMenuBebidas()
            }
        }
    }
}

data class Bebida(
    val imagen: Int,
    val nombre: String,
    val precio: String,
    val descripcion: String
)

val listaBebida = listOf(
    Bebida(R.drawable.bebida1, "Colombiana", "$8.000", "Refresco tradicional colombiano con sabor dulce y burbujeante."),
    Bebida(R.drawable.bebida2, "Coca Cola", "$8.000", "Bebida gaseosa clásica con un sabor inconfundible y refrescante."),
    Bebida(R.drawable.bebida3, "Jugo de maracuyá", "$9.000", "Jugo natural de maracuyá, con un toque ácido y exótico."),
    Bebida(R.drawable.bebida4, "Limonada Natural", "$10.000", "Limonada fresca hecha con limón real, perfecta para los días calurosos."),
    Bebida(R.drawable.bebida5, "Limonada de Coco", "$11.000", "Deliciosa mezcla de coco y limón, una explosión tropical en cada sorbo.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenuBebidas() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú") },
                actions = {
                    IconButton(onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_inicio),
                            contentDescription = "Inicio"
                        )
                    }

                    IconButton(onClick = {
                        context.startActivity(Intent(context, MenuPlatosActivity::class.java))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_platos),
                            contentDescription = "Platos"
                        )
                    }

                    IconButton(onClick = {
                        context.startActivity(Intent(context, MenuBebidasActivity::class.java))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_bebidas),
                            contentDescription = "Bebidas"
                        )
                    }

                    IconButton(onClick = {
                        context.startActivity(Intent(context, UbicacionesActivity::class.java))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_ubicaciones),
                            contentDescription = "Ubicaciones"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val url = "https://wa.me/573006797843"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
                containerColor = Color(0xFF25D366),
                modifier = Modifier
                    .size(56.dp)
            ) {
                Icon(painterResource(R.drawable.logowpp), contentDescription = "WhatsApp", tint = Color.Unspecified)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(listaBebida) { Bebida ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = Bebida.imagen),
                            contentDescription = Bebida.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(Bebida.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(Bebida.precio, color = Color(0xFF388E3C), fontSize = 16.sp)
                        Text(Bebida.descripcion, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
