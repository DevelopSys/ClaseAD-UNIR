package com.example.tiendaandroid

import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiendaandroid.ui.theme.TiendaAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiendaAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val lista = remember { List(50) { item -> "Item $item" } }


    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(lista) { item ->
            @Composable {
                createItem(item)
            }
        }
    }
}

@Composable
fun createItem(item: String) {
    // gestiona el aspecto de cada uno de los elementso de la fila
    Row {
        Column {
            Text(item)
        }
        Column {
            Button(
                onClick = {}
            ) {
                Text("Pulsar para comprar")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiendaAndroidTheme {
        Greeting("Android")
    }
}