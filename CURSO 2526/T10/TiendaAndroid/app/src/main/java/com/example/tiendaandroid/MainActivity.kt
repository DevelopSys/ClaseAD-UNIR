package com.example.tiendaandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tiendaandroid.model.Producto
import com.example.tiendaandroid.ui.theme.TiendaAndroidTheme
import com.google.gson.Gson
import kotlinx.coroutines.launch

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
fun Greeting(context: Context = LocalContext.current, name: String, modifier: Modifier = Modifier) {

    val lista = remember { List(50) { item -> "Item $item" } }
    val listaProducto = remember { mutableStateListOf<Producto>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // todo lo que quieras en la corrutina
        coroutineScope.launch {
            val peticion = Volley.newRequestQueue(context)
            val jsonRequest: JsonObjectRequest =
                JsonObjectRequest(
                    "http://192.168.1.184:8080/api/productos/getAll",
                    {
                        val arrayJson = it.getJSONArray("data")
                        val gson = Gson()
                        for (i in 0..arrayJson.length() - 1) {
                            val producto: Producto = gson.fromJson(
                                arrayJson.getJSONObject(i).toString(),
                                Producto::class.java
                            )
                            Log.v("datos", producto.nombre)
                            listaProducto.add(producto)
                        }
                    }, {
                        Log.v("datos", it.toString())
                    })

            peticion.add(jsonRequest)
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listaProducto) { item ->
            createItem(item)
        }
    }
}

@Composable
fun createItem(item: Producto) {
    // gestiona el aspecto de cada uno de los elementso de la fila
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            Text(item.nombre)
        }
        Column(modifier = Modifier.weight(0.5f)) {
            Button(
                onClick = {}
            ) {
                Text("Pulsar para comprar")
            }
        }

    }
}

@Composable
fun createItem(item: String) {
    // gestiona el aspecto de cada uno de los elementso de la fila
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.weight(0.5f)) {
            Text(item)
        }
        Column(modifier = Modifier.weight(0.5f)) {
            Button(
                onClick = {}
            ) {
                Text("Pulsar para comprar")
            }
        }

    }
}

