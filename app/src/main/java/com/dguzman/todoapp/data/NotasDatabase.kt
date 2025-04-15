package com.dguzman.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dguzman.todoapp.models.Nota

@Database(entities = [Nota::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NotasDatabase: RoomDatabase() {

    abstract fun notasDao(): NotasDao


}