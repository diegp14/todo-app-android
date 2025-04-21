package com.dguzman.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dguzman.todoapp.models.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotasDao {

    @Query("SELECT * FROM notas ORDER BY date DESC")
    fun getAllNotas(): Flow<List<Nota>>

    @Query("SELECT * FROM notas WHERE id = :id")
    suspend fun getNotaById(id: Int): Nota

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNota(nota: Nota)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNota(nota: Nota)

    @Delete
    suspend fun deleteNota(nota: Nota)

}