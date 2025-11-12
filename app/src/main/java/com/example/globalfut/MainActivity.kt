package com.example.globalfut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.globalfut.core.navigation.BottomBar
import com.example.globalfut.core.navigation.MainNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomBar(navController = navController) }
            ) { paddingValues ->
                Box(modifier = Modifier.fillMaxSize()) {
                    MainNavGraph(navController = navController)
                }
            }
        }
    }
}
