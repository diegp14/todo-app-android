package com.dguzman.todoapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.dguzman.todoapp.components.ActionIcon
import com.dguzman.todoapp.components.CardNota
import com.dguzman.todoapp.components.NotaButton
import com.dguzman.todoapp.components.NotaInputText
import com.dguzman.todoapp.components.SwipeToRevealCard
import com.dguzman.todoapp.models.Nota

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotasScreen(
    modifier: Modifier = Modifier,
    notas: List<Nota>,
    onAddNota: (Nota) -> Unit,
    onRemoveNota: (Nota) -> Unit,
    onGetAllNotas: () -> Unit,
    onClickNota: (Int) -> Unit,

) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }



        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NotaInputText(
                text = title,
                label = "Título de la nota",
                maxLines = 1,
                focusRequester = focusRequester,
                onValueChange = { title = it }
            )
            NotaInputText(
                text = description,
                label = "Descripción de la nota",
                maxLines = 2,
                focusRequester = focusRequester2,
                onValueChange  = { description = it }
            )
            NotaButton(
                text = "Guarda tu nota",
                enabled = title.isNotEmpty() && description.isNotEmpty(),
                colors = ButtonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF4CAF50).copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                )
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
            HorizontalDivider(modifier = Modifier.padding(0.dp, 10.dp), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = Modifier.padding(10.dp, 0.dp)) {
                items(notas) {
                    nota ->
                    SwipeToRevealCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 2.dp),
                        actionButtons = listOf(
                            {
                                ActionIcon(
                                    modifier = Modifier.fillMaxHeight(),
                                    backgroundColor = Color(0xFFCE2F3F),
                                    icon = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White,
                                    size = 60.dp,
                                    onClick = {
                                        onRemoveNota(nota)
                                    }
                                )
                            },
                        )
                    ) {
                    CardNota(
                        nota = nota,
                        onRemoveNota = { onRemoveNota(it) },
                        onClickNota = { onClickNota(it) }
                    )
                    }
                    /*CardNota(
                    nota = nota,
                        onRemoveNota = {onRemoveNota(it)},
                        onClickNota = { onClickNota(it) }
                    )

                     */
                }
            }
        }
    }

