package com.dguzman.todoapp.repository

import com.dguzman.todoapp.data.NotasDao
import com.dguzman.todoapp.models.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotaRepository @Inject constructor(
    private val dao : NotasDao

) {
    fun getAllNotas(): Flow<List<Nota>> = dao.getAllNotas().flowOn(Dispatchers.IO).conflate()

    suspend fun addNota(nota: Nota) = dao.addNota(nota)

    suspend fun updateNota(nota: Nota) = dao.updateNota(nota)

    suspend fun deleteNota(nota: Nota) = dao.deleteNota(nota)
}