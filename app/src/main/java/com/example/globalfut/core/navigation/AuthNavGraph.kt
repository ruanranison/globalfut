package com.example.globalfut.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.globalfut.modules.auth.screens.LoginScreen
import com.example.globalfut.modules.auth.screens.RegisterScreen

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    onLoginSuccess: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(onLoginSuccess = onLoginSuccess)
        }
        composable("register") {
            RegisterScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
