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

class MenuPlatosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteTheme {
                PantallaMenuPlatos()
            }
        }
    }
}

data class Plato(
    val imagen: Int,
    val nombre: String,
    val precio: String,
    val descripcion: String
)

val listaPlatos = listOf(
    Plato(R.drawable.plato1, "Bandeja Paisa", "$25.000", "Plato típico con arroz, fríjoles, carne molida, chicharrón y más."),
    Plato(R.drawable.plato2, "Ajiaco", "$20.000", "Sopa tradicional con pollo, papa criolla y mazorca."),
    Plato(R.drawable.plato3, "Cazuela de Mariscos", "$30.000", "Deliciosa mezcla de mariscos en salsa cremosa."),
    Plato(R.drawable.plato4, "Arroz con Pollo", "$18.000", "Arroz salteado con verduras y pollo desmechado."),
    Plato(R.drawable.plato5, "Pescado Frito", "$28.000", "Pescado entero acompañado de patacón y ensalada.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenuPlatos() {
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
            items(listaPlatos) { plato ->
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
                            painter = painterResource(id = plato.imagen),
                            contentDescription = plato.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(plato.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(plato.precio, color = Color(0xFF388E3C), fontSize = 16.sp)
                        Text(plato.descripcion, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
