package com.dguzman.todoapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dguzman.todoapp.screens.Home
import com.dguzman.todoapp.screens.HomeScreen
import com.dguzman.todoapp.screens.Login
import com.dguzman.todoapp.screens.LoginScreen
import com.dguzman.todoapp.screens.NotaViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val notaViewModel: NotaViewModel = hiltViewModel()
    val notas = notaViewModel.notas.collectAsState().value
    NavHost(navController= navController, startDestination = Login){
        composable<Login>{
            LoginScreen {
                name -> navController.navigate(Home(name = name))
                Log.d("Navigation", "Navigating to Home")
            }
            }
        composable<Home>{
            backStackEntry ->
            val args: Home = backStackEntry.toRoute()
            HomeScreen(
                name = args.name,
                notas = notas,
                onAddNota = { notaViewModel.addNota(it) },
                onRemoveNota = { notaViewModel.removeNota(it) },
                onGetAllNotas = { notaViewModel.getAllNotas() },
            )
        }

    }


}