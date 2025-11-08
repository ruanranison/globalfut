package com.example.globalfut.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Players : Screen("players", "Jogadores", Icons.Filled.DirectionsRun)
    object Add : Screen("add", "Adicionar", Icons.Filled.Add)
    object Teams : Screen("teams", "Times", Icons.Filled.Shield)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person)
}
