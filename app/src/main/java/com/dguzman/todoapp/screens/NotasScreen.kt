package com.dguzman.todoapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.dguzman.todoapp.components.CardNota
import com.dguzman.todoapp.components.NotaButton
import com.dguzman.todoapp.components.NotaInputText
import com.dguzman.todoapp.models.Nota

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotasScreen(
    notas: List<Nota>,
    onAddNota: (Nota) -> Unit,
    onRemoveNota: (Nota) -> Unit,
    onGetAllNotas: () -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column {
        TopAppBar(title =  {
            Text(text = "Mis Notas")
        },
            actions = {
                Icon(Icons.Rounded.Notifications, contentDescription = "Notifications")
            }
            )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NotaInputText(
                text = title,
                label = "Escribe el título de la nota",
                maxLines = 1,
                focusRequester = focusRequester,
                onValueChange = { title = it }
            )
            NotaInputText(
                text = description,
                label = "Escribe la descripción de la nota",
                maxLines = 2,
                focusRequester = focusRequester2,
                onValueChange  = { description = it }
            )
            NotaButton(
              text = "Guarda tu nota",
                enabled = title.isNotEmpty() && description.isNotEmpty(),
            ){

                    // Aquí puedes agregar la lógica para guardar la nota
                    // Por ejemplo, puedes usar una base de datos o una lista para almacenar las notas
                    onAddNota(
                        Nota(
                            title = title,
                            description = description
                        )
                    )
                    title = ""
                    description = ""
                    focusRequester.requestFocus()

                }

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.padding(20.dp, 0.dp)) {
                items(notas) {
                    nota -> CardNota(
                    nota = nota,
                        onRemoveNota = {onRemoveNota(it)})
                }
            }
        }
    }
}
