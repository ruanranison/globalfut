package com.example.globalfut.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AppNavHost(
    navController: NavHostController,
    isUserLoggedIn: Boolean,
    onLoginSuccess: () -> Unit // ⬅️ novo parâmetro
) {
    if (isUserLoggedIn) {
        MainNavGraph(navController)
    } else {
        AuthNavGraph(
            navController = navController,
            onLoginSuccess = onLoginSuccess // repassa a função
        )
    }
}
