package com.dguzman.todoapp.data

import com.dguzman.todoapp.models.Nota

class NotaDataSource {
    fun loadNotas(): List<Nota> {
        return listOf(
            Nota(title = "Nota 1", description = "Descripción de la nota 1 ffjsdlñasdñdsadjasñdasdsñddfjsñdsaDASFASldsñfmsñdñfdmfdfñdfsdñfdmfñgdlfdfñfsdf"),
            Nota(title = "Nota 2", description = "Descripción de la nota 2"),
            Nota(title = "Nota 3", description = "Descripción de la nota 3"),
            Nota(title = "Nota 4", description = "Descripción de la nota 4"),
            Nota(title = "Nota 5", description = "Descripción de la nota 5")
        )
    }
}