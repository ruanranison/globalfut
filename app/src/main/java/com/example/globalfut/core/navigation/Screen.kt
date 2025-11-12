package com.example.globalfut.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Players : Screen("players", "Jogadores", Icons.Default.List)
    object Teams : Screen("teams", "Times", Icons.Default.Group)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)
}
