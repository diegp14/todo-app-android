package com.dguzman.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.dguzman.todoapp.data.NotaDataSource
import com.dguzman.todoapp.navigation.NavigationWrapper
import com.dguzman.todoapp.screens.NotaViewModel
import com.dguzman.todoapp.screens.NotasScreen
import com.dguzman.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val notaViewModel: NotaViewModel by viewModels()
//                    val notas = notaViewModel.notas.collectAsState().value
//                    NotasScreen(
//                        notas = notas,
//                        onAddNota = { notaViewModel.addNota(it) },
//                        onRemoveNota = { notaViewModel.removeNota(it) },
//                        onGetAllNotas = { notaViewModel.getAllNotas() },
//                    )
                    NavigationWrapper()
                }
            }
        }
    }
}

