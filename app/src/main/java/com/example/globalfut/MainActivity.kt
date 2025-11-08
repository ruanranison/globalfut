package com.example.globalfut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.globalfut.core.navigation.AppNavHost
import com.example.globalfut.core.navigation.BottomBar
import com.example.globalfut.core.navigation.Screen
import com.example.globalfut.core.ui.theme.GlobalFutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlobalFutTheme {
                val navController = rememberNavController()
                var isUserLoggedIn by remember { mutableStateOf(false) }

                Scaffold(
                    bottomBar = {
                        if (isUserLoggedIn) {
                            BottomBar(
                                navController = navController,
                                onAddClick = { navController.navigate(Screen.Add.route) }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        AppNavHost(
                            navController = navController,
                            isUserLoggedIn = isUserLoggedIn,
                            onLoginSuccess = { isUserLoggedIn = true }
                        )
                    }
                }
            }
        }
    }
}
