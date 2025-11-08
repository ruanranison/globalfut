package com.example.globalfut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.globalfut.core.navigation.AppNavHost
import com.example.globalfut.core.navigation.BottomBar
import com.example.globalfut.core.navigation.Screen
import com.example.globalfut.ui.theme.GlobalFutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { GlobalFutApp() }
    }
}

@Composable
fun GlobalFutApp() {
    GlobalFutTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    navController = navController,
                    onAddClick = { navController.navigate(Screen.Add.route) }
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                AppNavHost(navController = navController)
            }
        }
    }
}
