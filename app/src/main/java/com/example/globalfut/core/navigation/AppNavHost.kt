package com.example.globalfut.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.globalfut.modules.feature_home.HomeScreen
import com.example.globalfut.modules.feature_players.PlayersScreen
import com.example.globalfut.modules.feature_teams.TeamsScreen
import com.example.globalfut.modules.feature_profile.ProfileScreen
import com.example.globalfut.modules.feature_add.AddScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Players.route) { PlayersScreen() }
        composable(Screen.Teams.route) { TeamsScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.Add.route) { AddScreen() }
    }
}
