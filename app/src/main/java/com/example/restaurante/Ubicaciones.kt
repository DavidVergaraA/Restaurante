package com.example.restaurante

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurante.ui.theme.RestauranteTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

class UbicacionesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteTheme {
                PantallaUbicaciones()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaUbicaciones() {
    val context = LocalContext.current
    val medellin = LatLng(6.2442, -75.5812)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(medellin, 12f)
    }

    val sedes = listOf(
        Sede("Sede Envigado", "Cra. 43 #38a Sur-39", LatLng(6.169147300057584, -75.58854851351816), R.drawable.envigado),
        Sede("Sede Poblado", "Cl 10 #43A-30", LatLng(6.211044113410542, -75.57076760478377), R.drawable.poblado),
        Sede("Sede Laureles", "Av Nutibara #74-56", LatLng(6.245486201481924, -75.59563846430609), R.drawable.laureles),
        Sede("Sede Belén", "Calle 30A #80A-26", LatLng(6.232205709611569, -75.60072945053125), R.drawable.belen)
    )

    var sedeSeleccionada by remember { mutableStateOf<Sede?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ubicaciones") },
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
                    val uri = Uri.parse("https://wa.me/573006797843")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                },
                containerColor = Color(0xFF25D366),
                modifier = Modifier
                    .size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logowpp),
                    contentDescription = "WhatsApp",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                sedes.forEach { sede ->
                    Marker(
                        state = MarkerState(position = sede.coordenadas),
                        title = sede.nombre,
                        snippet = sede.direccion,
                        onClick = {
                            sedeSeleccionada = sede
                            false
                        }
                    )
                }
            }

            sedeSeleccionada?.let { sede ->
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(id = sede.foto),
                            contentDescription = sede.nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = sede.nombre, style = MaterialTheme.typography.titleLarge)
                        Text(text = sede.direccion, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

data class Sede(
    val nombre: String,
    val direccion: String,
    val coordenadas: LatLng,
    val foto: Int
)
