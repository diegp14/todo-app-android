package com.dguzman.todoapp.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dguzman.todoapp.data.NotaDataSource
import com.dguzman.todoapp.models.Nota
import com.dguzman.todoapp.repository.NotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotaViewModel @Inject constructor(
    private val repository: NotaRepository
) : ViewModel() {

    private val _notas = MutableStateFlow<List<Nota>>(emptyList())
    val notas = _notas.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotas().distinctUntilChanged()
                .collect { lista ->
                    if (lista.isEmpty()){
                        Log.d("NotaViewModel", "Lista vacia")
                    }
                    _notas.value = lista
                }
        }
    }

    fun addNota(nota: Nota) = viewModelScope.launch {
        repository.addNota(nota)
    }

    fun removeNota(nota: Nota) = viewModelScope.launch {
        repository.deleteNota(nota)
    }
    fun getAllNotas() = viewModelScope.launch {
        repository.getAllNotas()
    }


}