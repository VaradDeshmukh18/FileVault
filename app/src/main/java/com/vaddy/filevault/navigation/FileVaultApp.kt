package com.vaddy.filevault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vaddy.filevault.presentation.screens.AdminLogScreen
import com.vaddy.filevault.presentation.screens.AdminLoginScreen
import com.vaddy.filevault.presentation.screens.AdminScreen
import com.vaddy.filevault.presentation.screens.LoginScreen
import com.vaddy.filevault.presentation.screens.ProfileScreen
import com.vaddy.filevault.presentation.screens.SignupScreen
import com.vaddy.filevault.presentation.screens.UserLoginScreen
import com.vaddy.filevault.presentation.screens.UserScreen


@Composable
fun FileVaultApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "loginAdmin") {
        composable("loginUser") { UserLoginScreen(navController) }
        composable("loginAdmin") { AdminLoginScreen(navController) }
        composable("signupUser") { SignupScreen(navController, isAdmin = false) }
        composable("signupAdmin") { SignupScreen(navController, isAdmin = true) }
        composable("admin") { AdminScreen(navController) }
        composable("user/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
            UserScreen(navController, userId)
        }
        composable("profile/{userId}") { backStackEntry ->
            ProfileScreen(navController, backStackEntry.arguments?.getString("userId")?.toInt() ?: 0)
        }
        composable("adminLogs") { AdminLogScreen(navController) }
    }
}

