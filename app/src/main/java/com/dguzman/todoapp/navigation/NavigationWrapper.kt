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
import com.dguzman.todoapp.models.Nota
import com.dguzman.todoapp.screens.DetailNota
import com.dguzman.todoapp.screens.DetailNotaScreen
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
    val notaId: Int = 0
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
                onClickNota = { id ->
                    navController.navigate(DetailNota(notaId = id))
                    Log.d("Navigation", "Navigating to DetailNota with id: $id")
                },


            )
        }
        composable<DetailNota>{
            backStackEntry ->
            val args: DetailNota = backStackEntry.toRoute()
            DetailNotaScreen(notaId = args.notaId,
                navigateBack = {
                    navController.navigateUp()
                },
                onUpdateNota = { notaViewModel.updateNota(it) },
            )
        }

    }


}