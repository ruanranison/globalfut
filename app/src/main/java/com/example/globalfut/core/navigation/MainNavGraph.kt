package com.example.globalfut.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.globalfut.core.db.DatabaseHelper
import com.example.globalfut.core.network.RetrofitInstance
import com.example.globalfut.modules.feature_home.data.repository.HomeRepository
import com.example.globalfut.modules.feature_home.data.viewmodel.HomeViewModel
import com.example.globalfut.modules.feature_home.screens.HomeScreen
import com.example.globalfut.modules.feature_players.PlayersScreen
import com.example.globalfut.modules.feature_players.data.repository.PlayerPostRepository
import com.example.globalfut.modules.feature_players.data.viewmodel.PlayerPostViewModel
import com.example.globalfut.modules.feature_profile.ProfileScreen
import com.example.globalfut.modules.feature_teams.screens.TeamsScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = viewModel(
        initializer = {
            HomeViewModel(
                HomeRepository(
                    DatabaseHelper.getInstance(context).postDao(),
                    RetrofitInstance.homeApi
                )
            )
        }
    )
    val playersViewModel: PlayerPostViewModel = viewModel(
        initializer = {
            PlayerPostViewModel(
                PlayerPostRepository(
                    DatabaseHelper.getInstance(context).playerPostDao(),
                    RetrofitInstance.playersApi
                )
            )
        }
    )
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(
            viewModel = homeViewModel
        ) }
        composable(Screen.Players.route) { PlayersScreen(
            viewModel = playersViewModel
        ) }
        composable(Screen.Teams.route) { TeamsScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}
