@file:OptIn(ExperimentalMaterial3Api::class)

package mx.edu.tecnm.chih2.lanzarmonedacompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.tecnm.chih2.lanzarmonedacompose.ui.theme.LanzarMonedaComposeTheme

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    /*
    Clase principal de la aplicación "Lanzar Moneda"
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LanzarMonedaComposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LanzarMonedaCompose()
                }
            }
        }
    }

    @Preview
    @Composable
    fun LanzarMonedaCompose() {
        MonedaButtonAndImage(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
        )
    }

    @Composable
    fun MonedaButtonAndImage(modifier: Modifier = Modifier) {
        // Función que define la UI de la aplicación

        // Variables para almacenar el resultado del lanzamiento de la moneda
        var result by remember { mutableStateOf(1) }
        var aguilas by remember { mutableStateOf(0) }
        var sellos by remember { mutableStateOf(0) }
        var aguilas_porciento by remember { mutableStateOf(0.0) }
        var sellos_porciento by remember { mutableStateOf(0.0) }

        var showMenu by remember { mutableStateOf(false) }
        val context = LocalContext.current

        // Actualizar imagen según resultado del lanzamiento de la moneda
        val imageResource = when (result) {
            1 -> {
                R.drawable.aguila
            }

            2 -> {
                R.drawable.sello
            }

            else -> R.drawable.aguila
        }

        // El  nivel de Composición principal es una columna que contiene un menú superior
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Menú superior con opciones en un TopAppBar
            TopAppBar(
                title = { Text(text = getString(R.string.titulo)) },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(
                            context, getString(R.string.item_favorite),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = getString(R.string.item_favorite)
                        )
                    }

                    IconButton(onClick = {
                        showMenu = !showMenu
                    }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = getString(R.string.item_more_vert)
                        )
                    }

                    // Elementos del menu desplegable
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(getString(R.string.item_settings)) },
                            onClick = {
                                Toast.makeText(context, getString(R.string.item_settings),
                                    Toast.LENGTH_SHORT).show()
                            })
                        DropdownMenuItem(
                            text = { Text(getString(R.string.item_logout)) },
                            onClick = {
                                Toast.makeText(context, getString(R.string.item_logout),
                                    Toast.LENGTH_SHORT).show()
                            })
                        DropdownMenuItem(
                            text = { Text(getString(R.string.item_about)) },
                            onClick = {
                                val intent =
                                    android.content.Intent(this@MainActivity, About::class.java)
                                startActivity(intent)
                            })
                    }
                }
            )

            // Se define la UI de la aplicación
            Text(
                stringResource(R.string.titulo),
                fontSize = 38.sp,
                modifier = Modifier.padding(top = 46.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            // La imagen de la moneda se muestra en el centro de la pantalla
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(R.string.moneda_desc),
                modifier = Modifier
                    .padding(16.dp)
                    .height(150.dp)
                    .width(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Botón para lanzar la moneda que genera un número aleatorio entre 1 y 2
            // y actualiza el resultado y las estadísticas de aguilas y sellos
            Button(onClick = {
                result = (1..2).random()
                if (result == 1) {
                    aguilas++
                } else {
                    sellos++
                }

                aguilas_porciento = ((aguilas / (aguilas + sellos).toFloat()) * 100).toDouble()
                sellos_porciento = ((sellos / (aguilas + sellos).toFloat()) * 100).toDouble()

                aguilas_porciento = String.format("%.2f", aguilas_porciento).toDouble()
                sellos_porciento = String.format("%.2f", sellos_porciento).toDouble()
            })
            {
                Text(
                    stringResource(R.string.roll),
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Muestra las estadísticas de aguilas y sellos en dos columnas

            Text(
                stringResource(id = R.string.aguila)
                        + " " + aguilas.toString() + "  --  " +
                        (aguilas_porciento).toString() +
                        " %",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Barra de progreso para mostrar el porcentaje de aguilas y sellos
            LinearProgressIndicator(
                progress = { (aguilas_porciento / 100).toFloat() },
                color = Color.Blue,
                trackColor = Color.LightGray,
                modifier = Modifier
                    .width(200.dp)
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(id = R.string.sello)
                        + " " + sellos.toString() + "  --  " +
                        (sellos_porciento).toString() +
                        " %",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { (sellos_porciento / 100).toFloat() },
                color = Color.Blue,
                trackColor = Color.LightGray,
                modifier = Modifier
                    .width(200.dp)
                    .height(20.dp)
            )
        }
    }

}
