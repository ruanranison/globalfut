package com.example.globalfut.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AppNavHost(
    navController: NavHostController,
    isUserLoggedIn: Boolean,
    onLoginSuccess: () -> Unit
) {
    if (isUserLoggedIn) {
        MainNavGraph(navController)
    } else {
        AuthNavGraph(
            navController = navController,
            onLoginSuccess = onLoginSuccess
        )
    }
}
