package com.example.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceDataStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import com.example.datastore.datastore.StoreUserEmail
import com.example.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {
    private lateinit var datastore:DataStore<Preferences>
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreTheme {


                // A surface container using the 'background' color from the theme
               Scaffold(
                   drawerContent = {},
                content = {
                    Main()
                }
               )
            }
        }
    }


}


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Main(){
    var email by remember { mutableStateOf("") }
    val context= LocalContext.current
    val storeEmail=StoreUserEmail(context)
    val savedEmail=storeEmail.getEmai.collectAsState(initial = " ")

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(value = email, onValueChange ={email=it}, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
        Button(onClick = { GlobalScope.launch { storeEmail.saveEmail(email) } }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(text = "Add email ...")
        }
        Text(text = savedEmail.value.toString(), modifier = Modifier.padding(10.dp))
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataStoreTheme {
        Greeting("Android")
    }
}