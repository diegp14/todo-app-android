package com.dguzman.todoapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dguzman.todoapp.models.Nota


@Composable
fun HomeScreen(
    name: String,
    notas: List<Nota>,
    onAddNota: (Nota) -> Unit,
    onRemoveNota: (Nota) -> Unit,
    onGetAllNotas: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen $name")
        Spacer(modifier = Modifier.height(25.dp))
        NotasScreen(
            notas = notas,
            onAddNota = { onAddNota(it)  },
            onRemoveNota = { onRemoveNota(it) },
            onGetAllNotas = { onGetAllNotas },
        )
    }
}