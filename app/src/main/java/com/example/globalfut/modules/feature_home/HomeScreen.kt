package com.example.globalfut.modules.feature_home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.globalfut.core.ui.components.GFHeader

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true // deixa os ícones escuros (pretos)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GFHeader(
            showSearchBar = true,
            onProfileClick = { /* navega para Perfil */ },
            onNotificationClick = { /* abre notificações */ },
            onSearchClick = { query -> /* filtra jogadores */ }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Tela Home", style = MaterialTheme.typography.headlineSmall)
        }
    }
}
