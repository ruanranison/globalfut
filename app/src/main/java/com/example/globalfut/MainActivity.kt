package com.example.globalfut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.globalfut.core.AppContainer
import com.example.globalfut.core.navigation.MainNavGraph // Assumindo esta rota
import com.example.globalfut.core.navigation.GlobalFutBottomBar // Assumindo este componente
import com.example.globalfut.ui.theme.GlobalFutTheme // Assumindo este tema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppContainer.initialize(applicationContext)

        setContent {
            GlobalFutTheme {
                val playersViewModelFactory = remember { AppContainer.getPlayersViewModelFactory() }
                val navController = rememberNavController()


                PlayersScreen(
                    factory = playersViewModelFactory,
                    navController = rememberNavController()
                )

                Scaffold(
                    bottomBar = { GlobalFutBottomBar(navController = navController) }
                ) { paddingValues ->
                    Box(Modifier.padding(paddingValues)) {
                        MainNavGraph(
                            navController = navController,
                            playersViewModelFactory = playersViewModelFactory
                        )
                    }
                }
            }
        }
    }
}