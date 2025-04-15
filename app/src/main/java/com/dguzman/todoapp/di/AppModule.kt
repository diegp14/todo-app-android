package com.dguzman.todoapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dguzman.todoapp.data.NotasDao
import com.dguzman.todoapp.data.NotasDatabase
import com.dguzman.todoapp.repository.NotaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotasDatabase =
        Room.databaseBuilder(context, NotasDatabase::class.java, "db_notas")
            .fallbackToDestructiveMigrationFrom()
            .build()

    @Provides
    fun provideNotasDao(db: NotasDatabase): NotasDao = db.notasDao()

    @Provides
    @Singleton
    fun provideRepository(dao: NotasDao): NotaRepository = NotaRepository(dao)
}