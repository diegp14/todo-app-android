package com.dguzman.todoapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dguzman.todoapp.components.NotaInputText

@Composable
fun LoginScreen(navigateToHome: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column (modifier = Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login")
        Spacer(modifier = Modifier.height(25.dp))
        TextField(value = text, onValueChange = {text = it})
        Button(
            onClick = { navigateToHome(text) },
            modifier = Modifier
        ) {
            Text( text = "Navegar")
        }
    }
}