package mx.edu.tecnm.chih2.lanzarmonedacompose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import mx.edu.tecnm.chih2.lanzarmonedacompose.ui.theme.LanzarMonedaComposeTheme

class About : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LanzarMonedaComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = stringResource(R.string.title_activity_about),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "Activity $name!",
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.programador),
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            //navController.popBackStack()

            activity?.finish()
        })
        {
            Text(
                stringResource(R.string.button_regresar),
                fontSize = 24.sp
            )
        }
    }
}


