package com.dguzman.todoapp.screens

import android.R.style
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dguzman.todoapp.components.NotaButton
import com.dguzman.todoapp.components.NotaInputText
import com.dguzman.todoapp.models.Nota
import com.dguzman.todoapp.utils.Converters


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNotaScreen(
    notaId: Int,
    navigateBack: () -> Unit,
    onUpdateNota: (Nota) -> Unit,
){
    val notaViewModel: NotaViewModel = hiltViewModel()
    val nota = notaViewModel.notaSelected
    val focusRequester = remember { FocusRequester() }


    LaunchedEffect(notaId) {
         notaViewModel.getNotaById(notaId)

    }
    nota?.let {
        var descriptionInput by remember { mutableStateOf(nota.description) }
        var titleInput by remember { mutableStateOf(nota.title) }

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "Note Detail",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigateBack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) {
            innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(text = "Creation date: ${Converters.formatDate(nota.date.time)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                NotaInputText(
                    text = titleInput,
                    label = "Title",
                    maxLines =  2,
                    focusRequester =focusRequester,
                    onValueChange = {titleInput = it}
                )

                NotaInputText(
                    text = descriptionInput,
                    label = "Description",
                    maxLines =  2,
                    focusRequester =focusRequester,
                    onValueChange = {descriptionInput = it}
                )
                Spacer(modifier = Modifier.height(20.dp))
                NotaButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    text = "Actualizar",
                    enabled = titleInput.isNotEmpty() && descriptionInput.isNotEmpty(),
                    colors = ButtonColors(
                        containerColor = Color(0xFF216AEF),
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFF216AEF).copy(alpha = 0.5f),
                        disabledContentColor = Color.White.copy(alpha = 0.5f)
                    )

                ){
                    Log.d("Nota", "Actualizar")
                    onUpdateNota(
                        nota.copy(
                            title = titleInput,
                            description = descriptionInput
                        )

                    )
                    navigateBack()
                   // notaViewModel.updateNota(
                     //   nota.copy(
                       //     title = titleInput,
                         //   description = descriptionInput
                        //)
                    //)
                }
            }
        }

        /*Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TopAppBar(title =  {
                Text(
                    text = "Detalle de la nota ",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
        }
            )
            Spacer(modifier = Modifier.height(25.dp))
            NotaInputText(
                text = titleInput,
                label = "Title",
                maxLines =  2,
                focusRequester =focusRequester,
                onValueChange = {descriptionInput = it}
            )

            NotaInputText(
                text = descriptionInput,
                label = "Description",
                maxLines =  2,
                focusRequester =focusRequester,
                onValueChange = {descriptionInput = it}
                )
        }

         */
    }?:run {
        Text(text = "Cargando...")
    }
}