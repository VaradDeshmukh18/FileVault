package com.vaddy.filevault

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vaddy.filevault.presentation.screens.AdminScreen
import com.vaddy.filevault.presentation.screens.LoginScreen
import com.vaddy.filevault.presentation.screens.SignupScreen
import com.vaddy.filevault.presentation.screens.UserScreen


@Composable
fun FileVaultApp(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController)}
        composable("admin") { AdminScreen(navController) }
        composable("user") { UserScreen(navController) }
    }
}