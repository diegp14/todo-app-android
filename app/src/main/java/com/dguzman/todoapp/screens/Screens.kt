package com.dguzman.todoapp.screens

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Home(val name: String)

@Serializable
data class DetailNota(val notaId: Int)